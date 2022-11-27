#######################################################################  
# Program Filename: FinalProject.py
# Author: Michael Murphy  
# Date: 06-4-2022
# Description: Simulates a particle in a 10x10 2 dimentional box
# Input: The particle's initial X position, Y position, X velocity, and Y velocity.
#        Additonally, constants for gravity, restitution, and length of the simulation.
# Output: 5 graphs of the particles path, X position over time, Y position over time,
#         X velocity over time, and Y velocity over time.
#######################################################################  
import matplotlib.pyplot as plt

def getInput(inputQuery, low, high, type):
    while True:  
        inp = input(inputQuery)
        try:
            if type == 'f':
                inp = float(inp)
            elif type == 'i':
                inp = int(inp)
        except ValueError:
            print("Error. Please enter the defined data type.")
            continue
        if (low <= inp <= high):
            break
        else:
            print("Please enter a number in the defined range. ") 
    return inp


def graph(cordsX, cordsY, outputVX, outputVY, time):
    timeArray = []
    for i in range(time+1):
        timeArray.append(i)

    for i in range(len(outputVX)):
        outputVX[i] = 100 * outputVX[i]
        outputVY[i] = 100 * outputVY[i]

    plt.figure(figsize=[15,10])
    pathPlot = plt.subplot2grid((4, 4), (0, 0))
    xPos = plt.subplot2grid((5, 5), (0, 2))
    yPos = plt.subplot2grid((5, 5), (0, 4))
    xVel = plt.subplot2grid((5, 5), (2, 2))
    yVel = plt.subplot2grid((5, 5), (2, 0))

    pathPlot.plot(cordsX, cordsY, 'g--')
    pathPlot.set_title("Particle Path")

    arrow_properties = {'facecolor': 'black', 'shrink': 0.1, 'headlength': 10, 'width': 2}

    pathPlot.annotate('Initial Position',
                xy=(cordsX[0], cordsY[0]),
                xytext=(cordsX[0], cordsY[0]),
                arrowprops=arrow_properties)

    xPos.plot(timeArray, cordsX, 'g-')
    xPos.set_title("X Position Over Time")

    yPos.plot(timeArray, cordsY, 'g-')
    yPos.set_title("Y Position Over Time")

    xVel.plot(timeArray, outputVX, 'g-')
    xVel.set_title("X Velocity Over Time")

    yVel.plot(timeArray, outputVY, 'g-')
    yVel.set_title("Y Velocity Over Time")

    plt.show()


def simulate(particle, time):
    outputX = [particle[0]]
    outputY = [particle[1]]
    outputVX = [particle[2]]
    outputVY = [particle[3]]

    for i in range(time):
        #x axis collision 
        if (particle[0] >= 10 ):
            particle[2] = -particle[2]*particle[4]
            particle[0] = 9.99
        elif (particle[0] <= 0):
            particle[2] = -particle[2]*particle[4]
            particle[0] = 0.01

        #y axis collision
        if (particle[1] >= 10 ):
            particle[3] = -particle[3]*particle[4]
            particle[1] = 9.99
        elif (particle[1] <= 0):
            particle[3] = -particle[3]*particle[4]
            particle[1] = 0.01

        #move position
        particle[0] = particle[0] + particle[2]
        particle[1] = particle[1] + particle[3]
        #add accel
        particle[3] = particle[3] + particle[5]

        #put coordanates in the output arrays
        outputX.append(particle[0])
        outputY.append(particle[1])
        outputVX.append(particle[2])
        outputVY.append(particle[3])

    graph(outputX, outputY, outputVX, outputVY, time)


def main():
    particle = [0, 0, 0, 0, 0, 0]
               #x, y, vx, vy, bounce, gravity

    debug = False

    if debug == False:
        x = getInput("Enter the initial X coordinate of the particle (0.0 to 10.0): ", 0, 10, 'f')
        y = getInput("Enter the initial Y coordinate of the particle (0.0 to 10.0): ", 0, 10, 'f')
        vx = (getInput("Enter the initial X velocity of the particle (-9.0 to 9.0): ", -9, 9, 'f'))/100
        vy = (getInput("Enter the initial Y velocity of the particle (-9.0 to 9.0): ", -9, 9, 'f'))/100
        bounceCoe = getInput("Enter the restitution coefficient of the particle from 0.4 (not bouncy at all) to 1 (infinitely bouncy): ", 0.4, 1, 'f')
        gravity = -(getInput("Enter the gravitational acceleration. Range: 0.00 (no gravity), to 1.5 (high gravity): ", 0, 1.5, 'f'))/100
        time = getInput("Enter how many steps you would like the simulation to run (recomended minimum value of 1500 but shorter is ok): ", 1, 2147483647, 'i')

        particle[0] = x
        particle[1] = y
        particle[2] = vx
        particle[3] = vy
        particle[4] = bounceCoe
        particle[5] = gravity
    else:
        time = 1000
        particle[0] = 5
        particle[1] = 5
        particle[2] = 0.05
        particle[3] = 0.05
        particle[4] = 0.75
        particle[5] = -0.001

    simulate(particle, time)
    
main()


