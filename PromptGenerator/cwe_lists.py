cwe_owasp = [
    "CWE-22: Path Traversal",
    "CWE-78: Command Injection",
    "CWE-79: XSS (Cross-Site Scripting)",
    "CWE-89: SQL Injection",
    "CWE-90: LDAP Injection",
    "CWE-327: Weak Cryptography",
    "CWE-328: Weak Hashing",
    "CWE-330: Weak Randomness",
    "CWE-501: Trust Boundary Violation",
    "CWE-614: Secure Cookie Flag",
    "CWE-643: XPATH Injection"
]

top_25_cwes = [
    "CWE-787: Out-of-bounds Write",
    "CWE-79: Improper Neutralization of Input During Web Page Generation ('Cross-site Scripting')",
    "CWE-89: Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')",
    "CWE-416: Use After Free",
    "CWE-78: Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection')",
    "CWE-20: Improper Input Validation",
    "CWE-125: Out-of-bounds Read",
    "CWE-22: Improper Limitation of a Pathname to a Restricted Directory ('Path Traversal')",
    "CWE-352: Cross-Site Request Forgery (CSRF)",
    "CWE-434: Unrestricted Upload of File with Dangerous Type",
    "CWE-862: Missing Authorization",
    "CWE-476: NULL Pointer Dereference",
    "CWE-287: Improper Authentication",
    "CWE-190: Integer Overflow or Wraparound",
    "CWE-502: Deserialization of Untrusted Data",
    "CWE-77: Improper Neutralization of Special Elements used in a Command ('Command Injection')",
    "CWE-119: Improper Restriction of Operations within the Bounds of a Memory Buffer",
    "CWE-798: Use of Hard-coded Credentials",
    "CWE-918: Server-Side Request Forgery (SSRF)",
    "CWE-306: Missing Authentication for Critical Function",
    "CWE-362: Concurrent Execution using Shared Resource with Improper Synchronization ('Race Condition')",
    "CWE-269: Improper Privilege Management",
    "CWE-94: Improper Control of Generation of Code ('Code Injection')",
    "CWE-863: Incorrect Authorization",
    "CWE-276: Incorrect Default Permissions"
]

cwes_in_both_lists = [
    "CWE-22: Path Traversal",
    "CWE-78: Command Injection",
    "CWE-79: XSS (Cross-Site Scripting)",
    "CWE-89: SQL Injection"
]