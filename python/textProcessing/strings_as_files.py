with open("song.txt", "r") as BigFile:
    data = BigFile.readlines()

# Print each line
    for i in range(len(data)):
        print("Line No-", i)
        print(data[i])
