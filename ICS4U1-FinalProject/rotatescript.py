from PIL import Image

troopName = "Skeleton" # Change to troop name accordingly
# Get move and attack paths
movePath = "images/Troops/" + troopName + "/" + troopName + "Move"
attackPath = "images/Troops/" + troopName + "/" + troopName + "Attack"
Min = 0
Max = 12

while True:
    try:
        angle = int(input("Enter counterclockwise rotation angle (negative for clockwise): "))
        break
    except ValueError:
        print("Invalid angle.")

# Move images
for i in range(Min, Max):
    img = Image.open(movePath + str(i) + ".png")
    ret = img.rotate(angle)
    ret.save(movePath + str(i) + ".png")

# Attack images
for i in range(Min, Max):
    img = Image.open(attackPath + str(i) + ".png")
    ret = img.rotate(angle)
    ret.save(attackPath + str(i) + ".png")
