from PIL import Image

troopName = "Knight" # Change to troop name accordingly
# Get move and attack paths
movePath = "images/Troops/" + troopName + "/" + troopName + "Move"
attackPath = "images/Troops/" + troopName + "/" + troopName + "Attack"

while True:
    try:
        angle = int(input("Enter counterclockwise rotation angle (negative for clockwise): "))
        break
    except ValueError:
        print("Invalid angle.")

for i in range(12):
    img = Image.open(movePath + str(i) + ".png")
    ret = img.rotate(angle)
    ret.save(movePath + str(i) + ".png")

for i in range(12):
    img = Image.open(attackPath + str(i) + ".png")
    ret = img.rotate(angle)
    ret.save(attackPath + str(i) + ".png")
