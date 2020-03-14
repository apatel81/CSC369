
import sys
import random


def generateStores(filename):

    # filename = sys.argv[1]
    file = open(filename, "r")

    numLines = 0
    for line in file:
        numLines += 1
    file.close()

    randLines = random.sample(range(1, numLines), 100)
    count = 0
    linenum = 1
    L = []
    # columns = "ID, storeName, address, city, ZIP, state, phoneNumber" + "\n"
    # L.append(columns)

    file = open(filename, "r")
    for line in file:
        if count in randLines:
            a = line.split(",")
            id = linenum
            store = a[1]
            address = a[2]
            city = a[3]
            zip = a[5]
            state = a[4]
            phone = a[9]

            if len(phone) == 0:
                part1 = random.randint(100, 999)
                part2 = random.randint(100, 999)
                part3 = random.randint(1000, 9999)
                number = str(part1) + " " + str(part2) + " " + str(part3)
            elif phone[0] != "+":
                part1 = random.randint(100, 999)
                part2 = random.randint(100, 999)
                part3 = random.randint(1000, 9999)
                number = str(part1) + " " + str(part2) + " " + str(part3)
            else:
                number = phone[2:5] + " " + phone[5:8] + " " + phone[8:12]

            final = str(id) + ", " + str(store) + ", " + str(address) + ", " + str(city) + ", " \
                + str(zip) + ", " + str(state) + ", " + str(number) + "\n"

            L.append(final)
            count += 1
            linenum += 1
        else:
            count += 1

    outfile = open("outputStores.csv", "w")
    outfile.writelines(L)
    outfile.close()

    file.close()


def generateCustomers(filename):
    # filename = sys.argv[1]
    file = open(filename, "r")

    numLines = 0
    for line in file:
        numLines += 1
    file.close()

    randLines = random.sample(range(0, numLines), 1000)
    count = 0
    linenum = 1
    L = []
    # columns = "ID, name, birth date, address, city, ZIP, state, phoneNumber" + "\n"
    # L.append(columns)

    file = open(filename, "r")
    for line in file:
        if count in randLines:
            a = line.split(",")
            id = str(linenum)
            name = str(a[1]) + " " + str(a[2])
            year = str(a[6])
            day = str(a[4][1:3])
            address = str(a[9][0:-1])
            city = str(a[10])
            zip = str(a[11])
            state = str(a[12].rstrip())
            phoneNumber = str(a[7][1:4]) + " " + str(a[8][0:3]) + " " + str(a[8][4:8])

            if a[3] == "Jan.":
                month = "01"
            elif a[3] == "Feb.":
                month = "02"
            elif a[3] == "Mar.":
                month = "03"
            elif a[3] == "Apr.":
                month = "04"
            elif a[3] == "May":
                month = "05"
            elif a[3] == "Jun.":
                month = "06"
            elif a[3] == "Jul.":
                month = "07"
            elif a[3] == "Aug.":
                month = "08"
            elif a[3] == "Sep.":
                month = "09"
            elif a[3] == "Oct.":
                month = "10"
            elif a[3] == "Nov.":
                month = "11"
            else:
                month = "12"

            birthday = year + "/" + month + "/" + day

            final = str(id) + ", " + str(name) + ", " + str(birthday) + ", " + str(address) + ", " \
                    + str(city) + ", " + str(zip) + ", " + str(state) + ", " + str(phoneNumber) + "\n"

            L.append(final)
            count += 1
            linenum += 1

        else:
            count += 1

    outfile = open("outputCustomer.csv", "w")
    outfile.writelines(L)
    outfile.close()

    file.close()


def generateSales():
    L = []
    # columns = "ID, date, time, storeID, customerID" + "\n"
    # L.append(columns)
    for i in range(1, 2001):
        id = i
        year = random.randint(2000, 2019)
        month = random.randint(1, 12)

        if month in [1, 3, 5, 7, 8, 10, 12]:
            day = random.randint(1,31)
        elif month == 2:
            day = random.randint(1,28)
        else:
            day = random.randint(1,30)

        if month in range(10):
            month = "0" + str(month)

        if day in range(10):
            day = "0" + str(day)

        date = str(year) + "/" + str(month) + "/" + str(day)
        hour = random.randint(0,24)
        minute = random.randint(0,60)
        seconds = random.randint(0,60)
        if hour <= 9:
            hour = "0" + str(hour)
        if minute <= 9:
            minute = "0" + str(minute)
        if seconds <= 9:
            seconds = "0" + str(seconds)

        time = str(hour) + ":" + str(minute) + ":" + str(seconds)
        if i <= 100:
            storeID = i
        else:
            storeID = random.randint(1, 100)
        if i <= 1000:
            customerID = i
        else:
            customerID = random.randint(1, 1000)

        final = str(id) + ", " + str(date) + ", " + str(time) + ", " + str(storeID) + ", " + str(customerID) + "\n"
        L.append(final)

    outfile = open("outputSales.csv", "w")
    outfile.writelines(L)
    outfile.close()


def generateProducts(filename):
    file = open(filename, "r")

    count = 1
    L = []
    # columns = "ID, description, price" + "\n"
    # L.append(columns)

    for line in file:
        id = count
        product = str(line).rstrip()
        dollars = random.randint(0,50)
        cents = random.randint(10,99)
        price = str(dollars) + "." + str(cents)

        final = str(id) + ", " + str(product) + ", " + str(price) + "\n"

        L.append(final)
        count += 1

    outfile = open("outputProduct.csv", "w")
    outfile.writelines(L)
    outfile.close()

    file.close()

def generateLineItems():
    L = []
    # columns = "ID, salesID, productID, quantity" + "\n"
    # L.append(columns)
    for i in range(1, 4001):
        id = i
        sales = random.randint(1, 2000)
        product = random.randint(1, 100)
        quantity = random.randint(1, 20)

        final = str(id) + ", " + str(sales) + ", " + str(product) + ", " + str(quantity) + "\n"
        L.append(final)

    outfile = open("outputLineItems.csv", "w")
    outfile.writelines(L)
    outfile.close()
