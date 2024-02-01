# Nay Thuya Oo

def merge_sort(arr):
    if len(arr) > 1:

        mid = len(arr)
        left = arr[:mid]
        right = arr[mid:]

        merge_sort(left)
        merge_sort(right)

        i = j = k = 0

        while i < len(left) and j < len(right):
            if left[i] <= right[j]:
                arr[k] = left[i]
                i += 1
            else:
                arr[k] = right[j]
                j += 1
            k += 1

        while i < len(left):
            arr[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            arr[k] = right[j]
            j += 1
            k += 1


file = open('input.txt', 'r')
inputSlice = []
count = 0
while 1:
    char = file.read(1)  # Reach file by character
    if not char:
        break

    inputSlice.append(char)  # append character to input slice
    count = count + 1

file.close()

merge_sort(inputSlice)

file = open('output.txt', 'w')

# write to output file
for index in range(len(inputSlice)):
    file.write(inputSlice[index])

file.close()
