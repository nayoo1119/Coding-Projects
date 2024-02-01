# Nay Thuya Oo

def isValid(s: str) -> bool:
    # Map that stores different closed and open brackets as key value pairs
    bracket_map = {')': '(', '}': '{', ']': '['}
    stack = []  # creating an empty slice
    for i in s:
        if i in ['{', '(', '[']:  # If it's an open bracket, Append it to slice
            stack.append(i)
        elif i in ['}', ')', ']']:
            # If it's a closed bracket, check if the last element in the slice is corresponding open bracket
            if len(stack) < 1:
                return False
            if bracket_map[i] == stack[-1]:
                stack.pop()
            else:
                return False

    if len(stack) != 0:
        return False

    return True
