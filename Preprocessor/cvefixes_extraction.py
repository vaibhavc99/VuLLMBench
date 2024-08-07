import pandas as pd
import sqlite3
from sqlite3 import Error
import os
import logging

logging.basicConfig(level=logging.DEBUG)

def create_connection(db_file):
    """
    Create a connection to the SQLite3 database.
    """
    conn = None
    try:
        conn = sqlite3.connect(db_file, timeout=10)
    except Error as e:
        logging.error(e)
    return conn

CVEFIXES_PATH = "CodeExamples/CVEfixes_v1.0.7"
DATA_PATH = os.path.join(CVEFIXES_PATH, 'Data')
OUTPUT_PATH = os.path.join(CVEFIXES_PATH, 'Output')

def get_cve_records_by_file(lang, ext):
    """
    Fetch CVE records by file from the database for the given programming language and file extension.
    """
    logging.info("Reading CVEfixes database")
    conn = create_connection(os.path.join(DATA_PATH, "CVEfixes.db"))
    
    logging.info("Running query for records before changes")
    query = f"""
    SELECT cv.cve_id, f.filename, f.old_path, f.new_path, f.num_lines_added, f.num_lines_deleted, 
           f.code_before, f.nloc, cc.cwe_id, cw.cwe_name, c.repo_url
    FROM file_change f, commits c, fixes fx, cve cv, cwe_classification cc, cwe cw
    WHERE f.hash = c.hash 
    AND c.hash = fx.hash 
    AND fx.cve_id = cv.cve_id 
    AND cv.cve_id = cc.cve_id 
    AND cw.cwe_id = cc.cwe_id
    AND f.programming_language='{lang}'
    """
    cvefixes_records = pd.read_sql_query(query, conn)
    
    logging.info("Running query for records after changes")
    query2 = f"""
    SELECT cv.cve_id, f.filename, f.old_path, f.new_path, f.num_lines_added, f.num_lines_deleted, 
           f.code_after, f.nloc, cc.cwe_id, cw.cwe_name, c.repo_url
    FROM file_change f, commits c, fixes fx, cve cv, cwe_classification cc, cwe cw
    WHERE f.hash = c.hash 
    AND c.hash = fx.hash 
    AND fx.cve_id = cv.cve_id 
    AND cv.cve_id = cc.cve_id 
    AND cw.cwe_id = cc.cwe_id
    AND f.programming_language='{lang}'
    """
    cvefixes_records_no_vul = pd.read_sql_query(query2, conn)

    # Combine the records before and after changes
    cvefixes_records = pd.concat([cvefixes_records, cvefixes_records_no_vul])
    
    # Create 'real_vulnerability' column to indicate if the 'code_after' is not null
    cvefixes_records['real_vulnerability'] = cvefixes_records['code_after'].apply(lambda x: isinstance(x, str))
    
    # Create 'code' column to store the code after changes, if available
    cvefixes_records['code'] = cvefixes_records['code_before']
    cvefixes_records['code'] = cvefixes_records['code'].fillna(cvefixes_records['code_after'])
    
    # Filter records to include only files with the specified extension
    df = cvefixes_records[cvefixes_records['filename'].str.endswith(ext)]
    
    logging.info("Saving to CSV")
    df.to_csv(os.path.join(OUTPUT_PATH, f'cvefixes_{lang.lower()}.csv'), index=False)
    
    logging.info(df.head(5))
    logging.info(f"Total records: {len(df)}")

def get_cve_records_by_method(lang, ext):
    """
    Fetch CVE records by method from the database for the given programming language and file extension.
    """
    logging.info("Reading CVEfixes database")
    conn = create_connection(os.path.join(DATA_PATH, "CVEFixes.db"))
    
    logging.info("Running query for method-level records")
    query = f"""
    SELECT cv.cve_id, f.filename, f.old_path, f.new_path, mc.name, mc.code, mc.nloc, mc.before_change, 
           cc.cwe_id, cw.cwe_name, c.repo_url
    FROM file_change f, commits c, fixes fx, cve cv, cwe_classification cc, cwe cw, method_change mc
    WHERE f.hash = c.hash 
    AND c.hash = fx.hash 
    AND fx.cve_id = cv.cve_id 
    AND cv.cve_id = cc.cve_id 
    AND cw.cwe_id = cc.cwe_id
    AND f.file_change_id = mc.file_change_id
    AND f.programming_language='{lang}'
    """
    cve_records = pd.read_sql_query(query, conn)
    
    # Indicate if the method contains vulnerabilities
    cve_records['real_vulnerability'] = cve_records['before_change']
    
    # Filter records to include only files with the specified extension
    df = cve_records[cve_records['filename'].str.endswith(ext)]
    
    logging.info("Saving to CSV")
    df.to_csv(os.path.join(OUTPUT_PATH, f'cvefixes_{lang.lower()}_method.csv'), index=False)
    
    logging.info(df.head(5))
    logging.info(f"Total records: {len(df)}")

if __name__ == '__main__':
    get_cve_records_by_file("Java", ".java")
    get_cve_records_by_method("Java", ".java")
