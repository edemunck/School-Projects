import turtle
import random

## I'm not sure if this is normal but when I run the program
## in the terminal as just 'python3 minesweeper.py' it brings up the
## screen and then closes it right away. But if I run it as
## 'python3 -i minesweeper.py' it will work normally. I have no idea
## if that's what it's supposed to do but I really hope so :)

class Cell:
    def __init__(self,t=turtle.Turtle(visible=False),llx=None,lly=None,width=None,height=None):
        self.__width=width
        self.__height=height
        self.__xmin=llx
        self.__ymin=lly
        self.__xmax=width
        self.__ymax=height
        self.__t=t
        self.__bomb=False
        self.__cleared=False
        self.__t.penup()
    def isIn(self,x,y):
        ## tells if the coordinates clicked are inside the grid
        if x>=0 and x<self.__width and y>=0 and y<self.__height:
            return True
        else:
            return False
    def setBomb(self):
        ## sets the cell as a bomb
        self.__bomb=True
    def isBomb(self):
        ## tells if the cell contains a bomb or not
        if self.__bomb==True:
            return True
        else:
            return False
    def clear(self):
        ## indicates that a cell has cleared and redraws it
        self.__cleared=True
        self.__t.pendown()
        self.__t.begin_fill()
        for l in range(4):
            self.__t.fd(15)
            self.__t.left(90)
        self.__t.end_fill()
        self.__t.penup()
    def isCleared(self):
        ## tells if a specific cell is cleared or not
        if self.__cleared==True:
            return True
        else:
            return False
    def showCount(self,c):
        ## go to center of individual cell and display c
        self.__t.write(c,font=("Arial",10,"normal"))
    def draw(self):
        ## draws the cells
        screen=self.__t.getscreen()
        screen.tracer(0,0)
        self.__t.speed(0)
        self.__t.setpos(0,0)
        moveup=0
        for k in range(int(self.__width/15)):
            forward=15
            for i in range(int(self.__height/15)):
                ## make each cell in the rows
                self.__t.pendown()
                l=0
                self.__t.fillcolor("green")
                self.__t.begin_fill()
                while l<4:
                    self.__t.fd(15)
                    self.__t.left(90)
                    l+=1
                self.__t.end_fill()
                self.__t.penup()
                self.__t.setpos(0+forward,0+moveup)
                forward+=15
            moveup+=15
            self.__t.setpos(0,0+moveup)
        screen.update()



class Minesweeper:
    def __init__(self,rows,cols,mines,vis=False):
        self.__t=turtle.Turtle(visible=False)
        self.__s=self.__t.getscreen()
        self.__rows=rows
        self.__cols=cols
        self.__mines=mines
        self.__vis=vis
        grid=[]
        for i in range(rows):
            row=[]
            for l in range(cols):
                row.append(Cell(t=self.__t,llx=0,lly=0,width=(rows*15),height=(cols*15)))
                ## makes each grid object (each cell) into a list of the rows, so that it can be indexed like a grid
            grid.append(row)
        self.__grid=grid
        for i in range(mines):
            f=random.randint(0,self.__rows-1)
            g=random.randint(0,self.__cols-1)
            if self.__grid[f][g].isBomb()==False:
                self.__grid[f][g].setBomb()
            else:
                i+=1
                ## sets the number of mines to a unique cell object
        self.__c=Cell(self.__t,llx=0,lly=0,width=(rows*15),height=(cols*15)) ## initializes cell class
        self.__c.draw() ## draws the grid
        self.__s.onclick(self.mouseClick) ## registers mouse clicks
        self.__s.listen()
    def countBombs(self,row,col):
        ## returns number of mines in neighboring rows and columns
        c=0
        x=row*15
        y=col*15
        for (ax,ay) in [(-1,-1),(1,-1),(-1,1),(1,1),(0,1),(1,0),(-1,0),(0,-1)]:
            ## this goes through each neighbor position one at a time. The if statement checks if the neighbor exists or not.
            if self.__c.isIn(x+ax*15,y+ay*15)==True and self.__grid[row+ax][col+ay].isBomb():
                c+=1
        if self.__grid[row][col].isBomb(): ## if the grid position is a bomb
            c=-1
        return c
    def cellsRemaining(self):
        ## returns the number of non-cleared cells. Says if the game is a win
        remains=(self.__rows*self.__cols)-self.__mines
        for l in range(self.__rows):
            for i in range(self.__cols):
                if self.__grid[l][i].isCleared():
                    remains-=1
        return remains
    def getRowCol(self,x,y):
        ## returns the grid location (takes x,y coords and converts to a place on the grid)
        row=int(x/15)
        col=int(y/15)
        if row>self.__rows or col>self.__cols or row<0 or col<0:
            ## if the click is not on the playing grid it returns -1,-1
            return -1,-1
        return row,col
    def mouseClick(self,x,y):
        ## responds to mouse clicks and plays the game, basically.
        self.__t.penup()
        row,col=self.getRowCol(x,y)
        if row!=-1 and col!=-1: ## if the click is on the grid, continue
            if self.cellsRemaining()!=0: ## if there are still cells left uncovered, continue
                c=self.countBombs(row,col)
                self.__t.goto(row*15,col*15)
                if c==-1: ## if a bomb is clicked
                    self.__vis=True
                elif c==0: ## if the cell clicked is empty
                    self.clearCell(row,col)
                else:
                    self.__t.fillcolor("grey")
                    self.__grid[row][col].clear()
                    self.__t.goto(row*15+5,col*15+5)
                    self.__c.showCount(c)
        if self.__vis==True or self.cellsRemaining()==0: ## if the game is over, clear the mines
            for l in range(self.__rows):
                for i in range(self.__cols):
                    if self.__grid[l][i].isBomb()==True:
                        self.__t.goto(l*15,i*15)
                        self.__t.fillcolor("red")
                        self.__grid[l][i].clear()
                        self.__t.goto(l*15+5,i*15+5)
                        self.__t.write('*',font=("Arial",10,"normal"))
            self.__t.goto(100,350)
            if self.__vis==True: ## if the game is lost, display YOU LOSE
                self.__t.write("You Lose (click to exit)",align="center",font=("Arial",20,"normal"))
            else: ## if the game is a win display YOU WIN!
                self.__t.write("You Win! Yay! (click to exit)",align="center",font=("Arial",20,"normal"))
            self.__s.exitonclick()
    def clearCell(self,row,col):
        ## recursive, starts with the cell at the specific row and col
        if row>=self.__rows or row<0 or col>=self.__cols or col<0 or self.__grid[row][col].isBomb() or self.__grid[row][col].isCleared():
            ## only clear that cell. These are base cases.
            return
        if self.countBombs(row,col)>0:
            ## only clear that cell. This is a base case.
            c=self.countBombs(row,col)
            self.__t.fillcolor("grey")
            self.__t.goto(row*15,col*15)
            self.__grid[row][col].clear()
            self.__t.goto(row*15+5,col*15+5)
            self.__c.showCount(c)
            return
        self.__t.fillcolor("grey")
        self.__t.goto(row*15,col*15)
        self.__grid[row][col].clear()
        for (ax,ay) in [(-1,-1),(1,-1),(-1,1),(1,1),(0,1),(1,0),(-1,0),(0,-1)]:
            self.clearCell(row+ax,col+ay)
            ## recursively clear neighboring cells.



def main():
    turtle.setworldcoordinates(-200,-200,400,400)
    turtle.hideturtle()
    play=Minesweeper(rows=14,cols=14,mines=15)

if __name__ == '__main__':
    main()
