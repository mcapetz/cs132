import subprocess

# Define the command to run the parser
parser_command = ["java", "claireParse"]

print("claire")

# Read the test cases from the P file
with open("P", "r") as file:
    test_cases = file.readlines()

# Iterate over each test case
for i, test_case in enumerate(test_cases, start=1):
    test_case = test_case.strip()
    if test_case[0:2] == "//":
        print(test_case)
        continue
    elif test_case == "":
        continue
    else:
        print(f"Test case {i}: {test_case}")

    # Run the parser with the test case
    process = subprocess.Popen(parser_command, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    stdout, stderr = process.communicate(input=test_case)

    # Check if the parsing was successful
    if process.returncode == 0:
        print("=> Passed")
    else:
        print("=> Failed")

    print()  # Add a newline for better readability
