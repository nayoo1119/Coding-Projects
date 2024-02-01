# Nay Thuya Oo


file = open('input.txt', 'r')
outputFile = open('output.txt', 'w')
inputSlice = ""
animalMap = {"cat": 1, "catfish": 2, "dog": 3, "dogfish": 4, "horse": 5, "seahorse": 6}

while 1:
    char = file.read(1)  # Read file by character
    if not char:
        break
    inputSlice=inputSlice+char  # append character to input slice

    if inputSlice in ["cat", "catfish", "dog", "dogfish", "horse", "seahorse"]:

        char = file.read(1)  # Read file by character
        if not char:
            outputFile.write(str(animalMap[inputSlice[:len(inputSlice)]]))
            break

        inputSlice = inputSlice + char

        if inputSlice[-1] == "f":
            continue
        else:
            # print(animalMap[inputSlice])
            outputFile.write(str(animalMap[inputSlice[:len(inputSlice)-1]]))
            inputSlice = inputSlice[-1]

file.close()

outputFile.close()
