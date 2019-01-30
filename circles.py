import turtle
import random

class Shape:
    def __init__(self,loc=(0,0),fill='',filled=False):
        self.loc=loc
        self.fill=fill
        self.filled=filled
    def setFillcolor(self,newfill):
        self.fill=newfill
    def setFilled(self,newbool):
        self.filled=newbool
    def isFilled(self):
        return self.filled

class Circle(Shape):
    def __init__(self,loc=(0,0),r=1,fill='',filled=False):
        super().__init__(loc,fill,filled)
        self.r=r
    def draw(self,turtle):
        turtle.penup()
        turtle.goto(self.loc)
        if self.filled==False:
            turtle.pendown()
            turtle.circle(self.r)
        else:
            turtle.pendown()
            turtle.fillcolor(self.fill)
            turtle.begin_fill()
            turtle.circle(self.r)
            turtle.end_fill()

class Display:
    def __init__(self):
        self.t=turtle.Turtle()
        scr=self.t.getscreen()
        elements=[]
        turtle.colormode(255)
        self.t.speed(0)
        self.t.hideturtle()
        self.t.penup()
        scr.onclick(self.mouseEvent)
        scr.listen()
    def mouseEvent(self,x,y):
        newr=random.randint(10,100)
        i=random.randint(0,255)
        j=random.randint(0,255)
        k=random.randint(0,255)
        # color=turtle.color(i,j,k)
        somethn=Circle(loc=(x,y),r=newr,fill=(i,j,k),filled=True)
        somethn.draw(self.t)
