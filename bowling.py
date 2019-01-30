import turtle
import random
import time

def pins():
    ##Sets up the pins
    turtle.speed(0)
    turtle.clear()
    turtle.shape("circle")
    turtle.color("red")
    turtle.goto(0,0)
    turtle.stamp()
    turtle.goto(30,40)
    turtle.stamp()
    turtle.goto(-30,40)
    turtle.stamp()
    turtle.goto(60,80)
    turtle.stamp()
    turtle.goto(0,80)
    turtle.stamp()
    turtle.goto(-60,80)
    turtle.stamp()
    turtle.goto(90,120)
    turtle.stamp()
    turtle.goto(30,120)
    turtle.stamp()
    turtle.goto(-30,120)
    turtle.stamp()
    turtle.goto(-90,120)
    turtle.stamp()


def knock1(fall):
    ##defines pin positions and knocks down pins for the first bowl of a frame, then returns the list of remaining pins
    turtle.penup()
    turtle.clear()
    turtle.goto(0,0)
    pin0=turtle.pos()
    turtle.goto(30,40)
    pin1=turtle.pos()
    turtle.goto(-30,40)
    pin2=turtle.pos()
    turtle.goto(60,80)
    pin3=turtle.pos()
    turtle.goto(0,80)
    pin4=turtle.pos()
    turtle.goto(-60,80)
    pin5=turtle.pos()
    turtle.goto(90,120)
    pin6=turtle.pos()
    turtle.goto(30,120)
    pin7=turtle.pos()
    turtle.goto(-30,120)
    pin8=turtle.pos()
    turtle.goto(-90,120)
    pin9=turtle.pos()
    pinls=[pin0,pin1,pin2,pin3,pin4,pin5,pin6,pin7,pin8,pin9] ##list of pin locations
    random.shuffle(pinls) ##shuffles the pin list to knock down 'random' pins
    z=0
    turtle.color("black")
    while z<fall: ##chooses the pins that are 'knocked down' and sets them as a dot
        turtle.penup()
        turtle.goto(pinls[z])
        turtle.write("X",False,align="center")
        z+=1
    while z>=fall and z<10: ##re-stamps the number of remaining pins
        turtle.color("red")
        turtle.shape("circle")
        turtle.goto(pinls[z])
        turtle.stamp()
        z+=1
    remainpins=pinls[fall:10] ##saves the list of remaining pins to be used in the second bowl of a frame
    return remainpins


def knock2(m,fall2o):
    ##knocks down the second number of pins from those remaining, using the list of remaining pins
    x=0
    turtle.penup()
    turtle.clearstamps()
    turtle.color("black")
    random.shuffle(m) ##reshuffles the list of remaining pins to knock down 'random' pins
    while x<fall2o: ##chooses the pins to 'knock down' from the user input and sets them as a dot
        turtle.goto(m[x])
        turtle.write("X",False,align="center")
        x+=1
    while x>=fall2o and x<len(m): ##resets the remaining pins (if any) as stamps
        turtle.color("red")
        turtle.shape("circle")
        turtle.goto(m[x])
        turtle.stamp()
        x+=1


def bowl(fall):
    ##indicates what to do when a certain number is entered
    if fall!=10:
        openframe(fall)
    elif fall==10:
        strike()


def bowl2(fall2):
    ##second bowl, indicates what to show for the remaining pins
    if fall2==10:
        spare()
    if fall2<10:
        openframe2(fall2)


def openframe(fall):
    ##used if the player does not get a strike
    turt2=turtle.Turtle()
    turt2.penup()
    turt2.hideturtle()
    if fall==0:
        turt2.pencolor("black")
        turt2.setpos(0,-100)
        turt2.write("Gutter Ball :(",False,align="center",font=("Arial",22,"normal"))
        time.sleep(2)
        turt2.clear()
    if fall==1 or fall==2 or fall==3 or fall==4 or fall==5 or fall==6 or fall==7 or fall==8 or fall==9:
        turt2.pencolor("black")
        turt2.setpos(75,-100)
        turt2.write("Open frame: ",False,align="right",font=("Arial",22,"normal"))
        turt2.write(fall,False,align="left",font=("Arial",22,"normal"))
        time.sleep(2)
        turt2.clear()


def openframe2(fall2):
    ##used if the player does not get a spare on their second bowl
    turt2=turtle.Turtle()
    turt2.penup()
    turt2.hideturtle()
    turt2.pencolor("black")
    if fall2==0:
        turt2.setpos(0,-100)
        turt2.write("Gutter Ball :(",False,align="center",font=("Arial",22,"normal"))
        time.sleep(2)
        turt2.clear()
        pins()
    if fall2==1 or fall2==2 or fall2==3 or fall2==4 or fall2==5 or fall2==6 or fall2==7 or fall2==8 or fall2==9:
        turt2.setpos(75,-100)
        turt2.write("Open frame: ",False,align="right",font=("Arial",22,"normal"))
        turt2.write(fall2,False,align="left",font=("Arial",22,"normal"))
        time.sleep(2)
        turt2.clear()
        pins()


def spare():
    ##if the user knocks down all the pins on their second try, they get a spare
    turt2=turtle.Turtle()
    turt2.penup()
    turt2.hideturtle()
    turt2.pencolor("black")
    turt2.setpos(0,-100)
    turt2.write("Spare!",False,align="center",font=("Arial",22,"normal"))
    time.sleep(2)
    turt2.clear()
    pins()


def strike():
    ##if user enters 10, all pins are knocked down and they do not get a second bowl
    turt2=turtle.Turtle()
    turt2.penup()
    turt2.hideturtle()
    turt2.pencolor("black")
    turt2.setpos(0,-100)
    turt2.write("Strike!",False,align="center",font=("Arial",22,"normal"))
    time.sleep(2)
    turt2.clear()
    pins()


def finalscore(pinlist):
    ##displays the final score for the user
    turtle.clear()
    i=0
    total=0
    while i<len(pinlist):
        if pinlist[i]==10: ##if the bowl is a strike, the next two scores are added onto it
            if (i+3)>=len(pinlist): ##no bonus points are awarded in the final frame for spares or strikes
                total+=pinlist[i]
            else:
                total=total+(pinlist[i]+pinlist[i+1]+pinlist[i+2])
        else:
            if (i+3)>=len(pinlist):
                total+=pinlist[i]
            else:
                if (pinlist[i]+pinlist[i+1])==10: ##if the bowl is a spare, the next one score is added onto it
                        total=total+(pinlist[i]+pinlist[i+1]+pinlist[i+2])
                else:
                    total=total+(pinlist[i]+pinlist[i+1])
                i+=1
        i+=1
    turtle.pencolor("black")
    turtle.setpos(0,0)
    turtle.write("Final Score: ",False,align="right",font=("Arial",22,"normal"))
    turtle.write(total,False,align="left",font=("Arial",22,"normal"))


def fallone(frame):
    ##prompts user input to determine the number of pins to be knocked down on the first bowl
    fall=turtle.textinput(frame,"Enter number of pins to be knocked down; enter nothing for random: ")
    if fall=="":  ##if nothing is entered, the bowl becomes a random number
        fall=random.randint(0,10)
    else:
        fall=int(fall)
        if fall>10:
            fall=10
    return fall


def falltwo(frame,fall):
    ##prompts user input to determine the number of pins to be knocked down on the second bowl (only used if player does not get a strike)
    fall2=turtle.textinput(frame,"Enter the next number of pins to be knocked down: ")
    if fall<=10:
        up=10-fall
    if fall2=="":
        fall2=random.randint(0,up)
    else:
        fall2=int(fall2)
        if fall2>(up):
            fall2=up
    return fall2


def main():
    turtle.penup()
    turtle.hideturtle()
    pins()
    pinlist=[]
    frame=1
    while frame<10: ##the first 9 frames, the player only gets a max of 2 bowls
        fall=fallone(frame)
        pinlist.append(fall)
        m=knock1(fall)
        bowl(fall)
        if fall<10:
            fall2=falltwo(frame,fall)+fall
            fall2o=fall2-fall
            knock2(m,fall2o)
            bowl2(fall2)
            pinlist.append(fall2-fall)
        frame+=1
    if frame==10: ##on the last frame the player gets a max of 3 bowls, but only if they get a strike or spare
        fall=fallone(frame)
        pinlist.append(fall)
        if fall==10: ##if the first bowl is a strike
            knock1(fall)
            bowl(fall)
            fall=fallone(frame)
            pinlist.append(fall)
            if fall==10: ##if second bowl is a strike
                knock1(fall)
                bowl(fall)
                fall=fallone(frame) ##if second bowl is a strike, one more frame is played
                knock1(fall)
                bowl(fall)
                pinlist.append(fall)
            else: ##if second bowl is not a strike
                m=knock1(fall)
                bowl(fall)
                fall2=falltwo(frame,fall)+fall
                fall2o=fall2-fall
                knock2(m,fall2o)
                bowl2(fall2)
                pinlist.append(fall2-fall)
        else: ##if first bowl of final frame is not a strike
            m=knock1(fall)
            bowl(fall)
            fall2=falltwo(frame,fall)+fall
            fall2o=fall2-fall
            knock2(m,fall2o)
            bowl2(fall2)
            pinlist.append(fall2-fall)
            if fall2==10: ##user gets a third bowl in final frame only if they get a spare on the second bowl
                fall=fallone(frame)
                knock1(fall)
                bowl(fall)
                pinlist.append(fall)
        finalscore(pinlist)
    turtle.getscreen()
    turtle.exitonclick()


if __name__ == '__main__':
    main()
