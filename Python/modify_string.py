def modify_string(s):
    if s[0].islower():
        return s.lower()
    else:
        return s.upper()

input_string = input("Enter a string: ")

modified_string = modify_string(input_string)
print("Modified string:", modified_string)