import turtle
import random

def thegrid():
    ## Sets up the grid with 40,000 False values. Wow.
    turtle.setworldcoordinates(0,0,199,199)
    turtle.penup()
    turtle.setpos(100,100)
    grid=[]
    for i in range(200):
        row=[]
        for l in range(200):
            row.append(False)
        grid.append(row)
    return grid


def inGrid(row,col):
    ## Tells you if a specific location is in a valid grid location
    if row-1<=0 or row+1>=199 or col-1<=0 or col+1>=199:
        ## the coord is outside the grid
        return False
    else:
        return True


def hasNeighbor(grid,row,col):
    ## Tells if a specific location has a particle adjacent to it.
    if inGrid(row,col)==True:
        if grid[row-1][col]==True or grid[row+1][col]==True or grid[row][col-1]==True or grid[row][col+1]==True:
            ## the particle has a neighbor
            return True
        elif grid[row-1][col-1]==True or grid[row-1][col+1]==True or grid[row+1][col-1]==True or grid[row+1][col+1]==True:
            return True
        else:
            return False
    else:
        return False


def moverow(decide,row):
        r=random.randint(1,2)
        if decide==1:
            if r==1:
                move=row-1
                ## Simulates a turn of 90 degrees
            if r==2:
                move=row+1
                ## Turn==270
            return move
        else:
            return row

def movecol(decide,col):
    r=random.randint(1,2)
    if decide==2:
        if r==1:
            move=col+1
            ## Turn==0
        if r==2:
            move=col-1
            ## Turn==180
        return move
    else:
        return col


def coords(grid,size):
    ## Draws the tree. Although mine sort of looks like a cluster. Sorry about that.
    turtle.speed(0)
    turtle.hideturtle()
    part=turtle.dot(5, "green")
    size-=1
    row=int(turtle.xcor())
    col=int(turtle.ycor())
    grid[row][col]=True
    R=1
    ## Radius starts out at R. After second particle is drawn it is R+1.
    m=101
    n=99
    ## Max x and y values after first dot is placed.
    while size>0:
        r=0
        turtle.setpos(100,100)
        turn=random.randint(0,360)
        turtle.left(turn)
        turtle.fd(R+1)
        x=turtle.xcor()
        y=turtle.ycor()
        row=int(round(x,0))
        col=int(round(y,0))
        while r<200:
            ## Abandons the particle if it does not attach after 200 moves.
            decide=random.randint(1,2)
            row=moverow(decide,row)
            col=movecol(decide,col)
            r+=1
            if hasNeighbor(grid,row,col)==True:
                if grid[row][col]==False: ## aka as long as there's not already a particle in that spot
                    turtle.goto(row,col)
                    grid[row][col]=True
                    turtle.dot(5,"green")
                    size-=1
                    r=201
                    if row>=m+1 or col>=m+1 or row<=n-1 or col<=n-1:
                        ## Increases radius if particle is farther from the center than any other.
                        m+=1
                        n-=1
                        R+=1
    return R,grid


def main():
    turtle.speed(0)
    turtle.hideturtle()
    turtle.getscreen()
    size=int(turtle.textinput("","Enter the number of particles: "))
    grid=thegrid()
    R,grid=coords(grid,size)
    turtle.exitonclick()


if __name__ == '__main__':
    main()
