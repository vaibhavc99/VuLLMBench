SIMPLE_SYSTEM_PROMPT = """
You are an assistant with expertise in identifying and explaining security vulnerabilities within code samples.
You will be asked to determine if a vulnerability is present in a given code sample and provide details about the type and name of the vulnerability.
When providing a response, answer exclusively in the JSON format having the following keys: 
  "vulnerability": <true or false>,
  "vulnerability_type": <CWE_number>,
  "vulnerability_name": <Name of CWE>
  
If there are no vulnerabilities, enter "N/A" in the other fields. Do not include any additional information in the response.
Base your analysis solely on the provided code. If user input is handled and sanitized in the code, then it should not be considered vulnerable.
Do not address or give any suggestions outside of the JSON response.

"""

SIMPLE_USER_PROMPT = """
Is there any vulnerability in the following code?
{code}
"""

# Vulnerability Specific Prompt
VUL_SYSTEM_PROMPT = SIMPLE_SYSTEM_PROMPT

VUL_USER_PROMPT = """
Please review the following code snippet to determine if it contains any vulnerabilities related to the specified Common Weakness Enumeration (CWE) categories.
CWE Categories: {cwe_list}

Code Snippet:
{code}
"""

HOLISTIC_VULNERABILITY_ANALYSIS_SYSTEM_PROMPT = """
You are an assistant with deep expertise in identifying, analyzing, and mitigating security vulnerabilities within code samples.

When analyzing the code, you should:

1. Determine if any vulnerabilities are present.
2. Identify the type of vulnerability based on the CWE classification.
3. Provide a detailed explanation of the vulnerability, including its nature, why it is considered a vulnerability, and the potential consequences if exploited.
4. Propose a solution or code modification that effectively mitigates the identified vulnerability.

Your response should be exclusively in JSON format with the following keys:
"vulnerability": <true or false>,
"vulnerability_type": <CWE_number>,
"vulnerability_name": <Name of CWE>,
"explanation": <detailed explanation of the vulnerability, including potential consequences and why it is considered a vulnerability>,
"solution": <detailed description of the solution or code modification, including how it mitigates the vulnerability>

If no vulnerabilities are found, enter "N/A" in the other fields. Do not include any additional information in the response.
"""

HOLISTIC_VULNERABILITY_ANALYSIS_USER_PROMPT = """
Please analyze the following code for vulnerabilities, provide insights, and suggest modifications to enhance security: 
{code}
"""


# SELF_REFLECTION_PROMPT = "Are the predictions provided in the previous response correct?"
# SELF_REFLECTION_PROMPT = "Are you sure code contains the vulnerability?"

# SELF_REFLECTION_PROMPT = """Are you sure? Answer in Yes or No format."""

SELF_REFLECTION_PROMPT = """
Are the predictions provided in the previous response correct? 
If so, please confirm by responding in the specified format. 
If not, please provide the correct response in the specified format. Do not include any additional information in the response.
"""

SELF_REFLECTION_WITH_GROUND_TRUTH_PROMPT = """
This is the ground truth: {ground_truth}.
Analyze the response again for correctness.
Are the predictions provided in the previous response correct? If so, please confirm by responding in the specified format. 
If not, please provide the correct response in the specified format. Do not include any additional information in the response.
"""

FEW_SHOT_SYSTEM_PROMPT = """
You are an assistant with expertise in identifying and explaining security vulnerabilities within code samples. You will be asked to determine if a vulnerability is present in a given code sample and provide details about the type and name of the vulnerability. When providing a response, answer exclusively in the JSON format having the following keys: 
  "vulnerability": <true or false>,
  "vulnerability_type": <CWE_number>,
  "vulnerability_name": <Name of CWE>
  
If there are no vulnerabilities, enter "N/A" in the other fields. Do not include any additional information in the response. Base your analysis solely on the provided code. If user input is handled and sanitized in the code, then it should not be considered vulnerable. Do not address or give any suggestions outside of the JSON response.

{examples}
"""

FEW_SHOT_USER_PROMPT = SIMPLE_USER_PROMPT

CHAIN_OF_THOUGHT_SYSTEM_PROMPT = """
You are an assistant with expertise in identifying and explaining security vulnerabilities within code samples.
You will be asked to determine if a vulnerability is present in a given code sample and provide details about the type and name of the vulnerability.
When providing a response, follow this chain of thought and answer exclusively in the JSON format having the following keys: 
  "vulnerability": <true or false>,
  "vulnerability_type": <CWE_number>,
  "vulnerability_name": <Name of CWE>
  
Chain of Thought:
1. Identify if there is any user input in the code (e.g., request parameters).
2. Determine if the user input is directly used in the code without proper handling or sanitization.
3. Check if the user input is used in sensitive operations (e.g., SQL queries, file operations).
4. Assess whether the user input handling prevents potential vulnerabilities.
5. Conclude if the code is vulnerable based on the above analysis.

If there are no vulnerabilities, enter "N/A" in the other fields. Do not include any additional information in the response.
Base your analysis solely on the provided code and consider the current context only, not potential better sanitization.
If user input is handled and sanitized in the code, then it should not be considered vulnerable.
Do not address or give any suggestions outside of the JSON response.

"""

CHAIN_OF_THOUGHT_USER_PROMPT = SIMPLE_USER_PROMPT

java_examples = """
Example 1:
User Prompt:
Is there any vulnerability in the following code?
```java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/example1")
public class Example1 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("input");
        if (param == null) param = "";

        String bar = doSomething(request, param);

        try {
            String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + bar + "'";
            org.example.DatabaseHelper.JDBCtemplate.batchUpdate(sql);
            response.getWriter().println("No results can be displayed for query: " + org.example.Encoder.encodeForHTML(sql) + "<br>" + " because the batchUpdate method doesn't return results.");
        } catch (org.springframework.dao.DataAccessException e) {
            if (org.example.DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
            } else throw new ServletException(e);
        }
    }

    private static String doSomething(HttpServletRequest request, String param)
            throws ServletException, IOException {
        String bar;
        int num = 106;
        bar = (7 * 18) + num > 200 ? "This_should_always_happen" : param;
        return bar;
    }
}
```

Response:
```json
{
  "vulnerability": false,
  "vulnerability_type": "N/A",
  "vulnerability_name": "N/A"
}
```

Example 2:
User Prompt:
Is there any vulnerability in the following code?
```java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/example2")
public class Example2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        
        try {
            // Execute query
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Response:
```json
{
  "vulnerability": true,
  "vulnerability_type": "CWE-89",
  "vulnerability_name": "SQL Injection"
}
```
"""

c_examples = """
Example 1: No Vulnerability (Negative Example)
User Prompt:
Is there any vulnerability in the following code?
```c
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int validate_and_convert(const char *input) {
    // Check if input is a valid integer
    for (int i = 0; input[i] != '\0'; i++) {
        if (!isdigit(input[i])) {
            return -1; // Invalid input
        }
    }
    return atoi(input); // Convert to integer
}

int main() {
    char input[20];
    printf("Enter a number: ");
    scanf("%19s", input);

    int result = validate_and_convert(input);
    if (result == -1) {
        printf("Invalid input detected.\\n");
    } else {
        printf("You entered: %d\\n", result);
    }

    return 0;
}```

Response:
```json
{
  "vulnerability": false,
  "vulnerability_type": "N/A",
  "vulnerability_name": "N/A"
}
```

Example 2: Vulnerability Present (Positive Example)

User Prompt:
Is there any vulnerability in the following code?
```c
#include <stdio.h>
#include <stdlib.h>

int convert_to_int(const char *input) {
    return atoi(input); // No validation on input
}

int main() {
    char input[20];
    printf("Enter a number: ");
    scanf("%19s", input);

    int result = convert_to_int(input);
    printf("You entered: %d\\n", result);

    return 0;
}
```

Response:
```json
{
  "vulnerability": true,
  "vulnerability_type": "CWE-20",
  "vulnerability_name": "Improper Input Validation"
}
```
"""

cpp_examples = """
Example 1: No Vulnerability (Negative Example)

User Prompt:
Is there any vulnerability in the following code?
```cpp
#include <iostream>
#include <cstring>

int main() {
    const int size = 10;
    char buffer[size];

    // Properly limit the input to avoid out-of-bounds writes
    std::cout << "Enter a string (max 9 characters): ";
    std::cin.getline(buffer, size);

    std::cout << "You entered: " << buffer << std::endl;

    return 0;
}
```

Response:
```json
{
  "vulnerability": false,
  "vulnerability_type": "N/A",
  "vulnerability_name": "N/A"
}
```

Example 2: Vulnerability Present (Positive Example)

User Prompt:
Is there any vulnerability in the following code?
```cpp
#include <iostream>
#include <cstring>

int main() {
    char buffer[10];

    // No boundary check, possible out-of-bounds write
    std::cout << "Enter a string: ";
    std::cin >> buffer;

    std::cout << "You entered: " << buffer << std::endl;

    return 0;
}
```

Response:
```json
{
  "vulnerability": true,
  "vulnerability_type": "CWE-787",
  "vulnerability_name": "Out-of-bounds Write"
}
```
"""

csharp_examples = """
Example 1: No Vulnerability (Negative Example)

User Prompt:
Is there any vulnerability in the following code?
```csharp
using System;
using System.IO;

public class Example1
{
    public static void Main(string[] args)
    {
        Console.WriteLine("Enter a file name:");
        string inputFileName = Console.ReadLine();

        string basePath = Path.GetFullPath("C:/files/");
        string fullPath = Path.GetFullPath(Path.Combine(basePath, inputFileName));

        // Check if the full path is still within the base directory
        if (fullPath.StartsWith(basePath, StringComparison.OrdinalIgnoreCase))
        {
            if (File.Exists(fullPath))
            {
                string content = File.ReadAllText(fullPath);
                Console.WriteLine("File content: " + content);
            }
            else
            {
                Console.WriteLine("File does not exist.");
            }
        }
        else
        {
            Console.WriteLine("Invalid file path.");
        }
    }
}
```
Response:
```json
{
  "vulnerability": false,
  "vulnerability_type": "N/A",
  "vulnerability_name": "N/A"
}
```

Example 2: Vulnerability Present (Positive Example)

User Prompt:
Is there any vulnerability in the following code?
```csharp
using System;
using System.IO;

public class Example2
{
    public static void Main(string[] args)
    {
        Console.WriteLine("Enter a file name:");
        string inputFileName = Console.ReadLine();

        string basePath = "C:/files/";
        string fullPath = basePath + inputFileName;

        if (File.Exists(fullPath))
        {
            string content = File.ReadAllText(fullPath);
            Console.WriteLine("File content: " + content);
        }
        else
        {
            Console.WriteLine("File does not exist.");
        }
    }
}
```
Response:
```json
{
  "vulnerability": true,
  "vulnerability_type": "CWE-22",
  "vulnerability_name": "Path Traversal"
}
```
"""

javascript_examples = """
Example 1: No Vulnerability (Negative Example)
User Prompt:
Is there any vulnerability in the following code?
```javascript
var commentSection = document.getElementById("commentSection");

function sanitizeInput(input) {
    // Create a temporary DOM element to escape HTML characters
    var tempDiv = document.createElement('div');
    tempDiv.textContent = input; // Automatically escapes dangerous characters
    return tempDiv.innerHTML;
}

function addComment() {
    var userInput = document.getElementById("userComment").value;
    
    // Sanitize the input before adding to the page
    var safeInput = sanitizeInput(userInput);

    var newComment = document.createElement("p");
    newComment.innerHTML = safeInput;  // Safely displays the sanitized input
    commentSection.appendChild(newComment);
}

document.getElementById("submitComment").addEventListener("click", function() {
    addComment();
});
```
Response:
```json
{
  "vulnerability": false,
  "vulnerability_type": "N/A",
  "vulnerability_name": "N/A"
}
```

Example 2: Vulnerability Present (Positive Example)

User Prompt:
Is there any vulnerability in the following code?
```javascript
var commentSection = document.getElementById("commentSection");

function addComment() {
    var userInput = document.getElementById("userComment").value;

    // Directly inserting user input into the DOM without sanitization
    var newComment = document.createElement("p");
    newComment.innerHTML = userInput;  // Potential XSS vulnerability
    commentSection.appendChild(newComment);
}

document.getElementById("submitComment").addEventListener("click", function() {
    addComment();
});
```
Response:
```json
{
  "vulnerability": true,
  "vulnerability_type": "CWE-79",
  "vulnerability_name": "Cross-site Scripting (XSS)"
}
```
"""


python_examples = """
Example 1: No Vulnerability (Negative Example)**
User Prompt:
Is there any vulnerability in the following code?
```python
import requests
from urllib.parse import urlparse

def is_safe_url(url):
    # Only allow URLs with specific allowed domains
    allowed_domains = ['trusted.com', 'example.com']
    
    parsed_url = urlparse(url)
    if parsed_url.netloc in allowed_domains:
        return True
    return False

def fetch_data(url):
    if is_safe_url(url):
        try:
            response = requests.get(url)
            return response.text
        except requests.exceptions.RequestException as e:
            return f"Error fetching the data: {str(e)}"
    else:
        return "Invalid URL or domain not allowed"

# Example usage
user_input_url = input("Enter a URL to fetch data from: ")
result = fetch_data(user_input_url)
print(result)
```
Response:
```json
{
  "vulnerability": false,
  "vulnerability_type": "N/A",
  "vulnerability_name": "N/A"
}

Example 2: Vulnerability Present (Positive Example)**
User Prompt:
Is there any vulnerability in the following code?
```python
import requests

def fetch_data(url):
    try:
        # No validation, allowing any URL to be fetched
        response = requests.get(url)
        return response.text
    except requests.exceptions.RequestException as e:
        return f"Error fetching the data: {str(e)}"

# Example usage
user_input_url = input("Enter a URL to fetch data from: ")
result = fetch_data(user_input_url)
print(result)
```
Response:
```json
{
  "vulnerability": true,
  "vulnerability_type": "CWE-918",
  "vulnerability_name": "Server-Side Request Forgery (SSRF)"
}

"""