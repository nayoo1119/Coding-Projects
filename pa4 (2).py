# Nay Thuya Oo

import csv

inputMap = {}

with open('input.csv') as csvfile:
    reader = csv.reader(csvfile, delimiter=',', quotechar='|')
    for row in reader:
        if len(row) < 4:
            continue
        else:
            if inputMap.get(row[0]) is None:
                inputMap[row[0]] = row
            else:
                duplRow = inputMap[row[0]]
                print("Duplicate SSN encountered!")
                print(
                    "Encountered SSN: " + row[0] + "\nEncountered First Name: " + row[1] + "\nEncountered Last Name: " +
                    row[2] + "\nEncountered DOB: " + row[3])
                print("Already Stored SSN: " + duplRow[0] + "\nAlready Stored First Name: " + duplRow[
                    1] + "\nAlready Stored Duplicate Last Name: " + duplRow[2] + "\nAlready Stored Duplicate DOB: " +
                      duplRow[3])

                break
