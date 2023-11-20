# Wormy (a Nibbles clone)
# By Al Sweigart al@inventwithpython.com
# http://inventwithpython.com/pygame
# Released under a "Simplified BSD" license

import random, pygame, sys
import time

from pygame.locals import *

FPS = 15
WINDOWWIDTH = 640
WINDOWHEIGHT = 480
CELLSIZE = 20
assert WINDOWWIDTH % CELLSIZE == 0, "Window width must be a multiple of cell size."
assert WINDOWHEIGHT % CELLSIZE == 0, "Window height must be a multiple of cell size."
CELLWIDTH = int(WINDOWWIDTH / CELLSIZE)
CELLHEIGHT = int(WINDOWHEIGHT / CELLSIZE)

#             R    G    B
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
DARKGREEN = (0, 155, 0)
PINK = (255, 182, 193)  # baranje 1: enemy crv boi
ORANGE = (255, 165, 0)  # baranje 2: boi za baranje 2
DARKORANGE = (204, 102, 0)
BROWN = (139, 69, 19)
DARKBROWN = (101, 67, 33)

DARKPINK = (139, 0, 139)
DARKGRAY = (40, 40, 40)
BGCOLOR = BLACK

UP = 'up'
DOWN = 'down'
LEFT = 'left'
RIGHT = 'right'

HEAD = 0  # syntactic sugar: index of the worm's head

score, bonus = 0, 0  # baranje 2: brojac za bonus poeni od bonus jabolka i za poeni

def main():
    global FPSCLOCK, DISPLAYSURF, BASICFONT

    pygame.init()
    FPSCLOCK = pygame.time.Clock()
    DISPLAYSURF = pygame.display.set_mode((WINDOWWIDTH, WINDOWHEIGHT))
    BASICFONT = pygame.font.Font('freesansbold.ttf', 18)
    pygame.display.set_caption('Wormy')

    showStartScreen()
    while True:
        runGame()
        showGameOverScreen()


def drawBonusApple(coord, color1, color2): # baranje 2
    x = coord['x'] * CELLSIZE
    y = coord['y'] * CELLSIZE
    for i in range(3):
        color1, color2 = color2, color1
        rect = pygame.Rect(x, y, CELLSIZE, CELLSIZE)
        pygame.draw.rect(DISPLAYSURF, color1, rect)
        pygame.display.update()


def runGame():
    # Set a random start point.
    starting_time = time.time()
    # baranje 2: slednite 3 linii se za inicijalizacija
    global bonus, score
    bonus = 0
    score = 0
    startx = random.randint(5, CELLWIDTH - 6)
    starty = random.randint(5, CELLHEIGHT - 6)
    wormCoords = [{'x': startx, 'y': starty},
                  {'x': startx - 1, 'y': starty},
                  {'x': startx - 2, 'y': starty}]
    direction = RIGHT

    # baranje 1: Inicijalizacija na startnite pozicii od enemy crv
    startx_enemy = random.randint(5, CELLWIDTH - 6)
    starty_enemy = random.randint(5, CELLHEIGHT - 6)

    enemyCoords = [{'x': startx_enemy, 'y': starty_enemy},
                   {'x': startx_enemy - 1, 'y': starty_enemy},
                   {'x': startx_enemy - 2, 'y': starty_enemy}]
    enemy_direction = DOWN

    orange_apple = getRandomLocation()  # baranje 2: random lokacija za bonus elementite(gi krstiv jabolka)
    brown_apple = getRandomLocation()
    while orange_apple == wormCoords[HEAD] or brown_apple == wormCoords[HEAD]:
        orange_apple = getRandomLocation()
        brown_apple = getRandomLocation()

    # Start the apple in a random place.
    apple = getRandomLocation()

    while True:  # main game loop
        for event in pygame.event.get():  # event handling loop
            if event.type == QUIT:
                terminate()
            elif event.type == KEYDOWN:
                if (event.key == K_LEFT or event.key == K_a) and direction != RIGHT:
                    direction = LEFT
                elif (event.key == K_RIGHT or event.key == K_d) and direction != LEFT:
                    direction = RIGHT
                elif (event.key == K_UP or event.key == K_w) and direction != DOWN:
                    direction = UP
                elif (event.key == K_DOWN or event.key == K_s) and direction != UP:
                    direction = DOWN
                elif event.key == K_ESCAPE:
                    terminate()

        # baranje 1: Dvizenje na enemy crv
        if (time.time() - starting_time) > 20:
            if enemy_direction == LEFT:
                enemy_direction = random.choice([UP, DOWN, LEFT])
            elif enemy_direction == RIGHT:
                enemy_direction = random.choice([UP, DOWN, RIGHT])
            elif enemy_direction == UP:
                enemy_direction = random.choice([LEFT, RIGHT, UP])
            elif enemy_direction == DOWN:
                enemy_direction = random.choice([RIGHT, DOWN, LEFT])

            if enemy_direction == UP:
                enemy_head = {'x': enemyCoords[HEAD]['x'], 'y': enemyCoords[HEAD]['y'] - 1}
            elif enemy_direction == DOWN:
                enemy_head = {'x': enemyCoords[HEAD]['x'], 'y': enemyCoords[HEAD]['y'] + 1}
            elif enemy_direction == LEFT:
                enemy_head = {'x': enemyCoords[HEAD]['x'] - 1, 'y': enemyCoords[HEAD]['y']}
            elif enemy_direction == RIGHT:
                enemy_head = {'x': enemyCoords[HEAD]['x'] + 1, 'y': enemyCoords[HEAD]['y']}
            enemyCoords.insert(0, enemy_head)

        # check if the worm has hit itself or the edge
        if wormCoords[HEAD]['x'] == -1 or wormCoords[HEAD]['x'] == CELLWIDTH or wormCoords[HEAD]['y'] == -1 or \
                wormCoords[HEAD]['y'] == CELLHEIGHT:
            return  # game over
        for wormBody in wormCoords[1:]:
            if wormBody['x'] == wormCoords[HEAD]['x'] and wormBody['y'] == wormCoords[HEAD]['y']:
                return  # game over

        # baranje 1: zgolemuvanje enemy crv
        if (time.time() - starting_time) > 20:
            if enemyCoords[HEAD] == wormCoords[HEAD]:
                pass  # ne go brishime posledniot segment kako pri normalno dvizenje
                # (vsusnost isto kako kaj originalniot crv koga jade jabolko)
            else:
                del enemyCoords[-1]

        # check if worm has eaten an apply
        if wormCoords[HEAD]['x'] == apple['x'] and wormCoords[HEAD]['y'] == apple['y']:
            # don't remove worm's tail segment
            apple = getRandomLocation()  # set a new apple somewhere
        elif (time.time() - starting_time) > 20 and enemyCoords[HEAD] == wormCoords[HEAD]:
            pass  # zgolemuvanje na crvot kako da izel jabolko pri kolizija so enemy crvot
        else:
            del wormCoords[-1]  # remove worm's tail segment

        # baranje 2: proverka dali crvot izel bonus jabolko
        if wormCoords[HEAD] == orange_apple and ((time.time() - starting_time) % 10) > 5:
            orange_apple = getRandomLocation()
            bonus += 3
        if wormCoords[HEAD] == brown_apple:
            brown_apple = {'x': -1, 'y': -1}
            bonus += 3

        # move the worm by adding a segment in the direction it is moving
        if direction == UP:
            newHead = {'x': wormCoords[HEAD]['x'], 'y': wormCoords[HEAD]['y'] - 1}
        elif direction == DOWN:
            newHead = {'x': wormCoords[HEAD]['x'], 'y': wormCoords[HEAD]['y'] + 1}
        elif direction == LEFT:
            newHead = {'x': wormCoords[HEAD]['x'] - 1, 'y': wormCoords[HEAD]['y']}
        elif direction == RIGHT:
            newHead = {'x': wormCoords[HEAD]['x'] + 1, 'y': wormCoords[HEAD]['y']}
        wormCoords.insert(0, newHead)
        DISPLAYSURF.fill(BGCOLOR)
        drawGrid()
        drawWorm(wormCoords, GREEN, DARKGREEN, )
        # baranje 1: go crtame enemy crv
        if (time.time() - starting_time) > 20:
            drawWorm(enemyCoords, PINK, DARKPINK)
        # baranje 2: slednite dva ifovi gi crtaat soodvetnite jabolka
        if ((time.time() - starting_time) % 10) > 5: # sekoi vtori 5 ednici od vremeto (primer od 6ta do 9ta sekunda) kje se crta jabolkoto
            drawBonusApple(orange_apple, ORANGE, DARKORANGE)
        if (time.time() - starting_time) < 7: #ednas samo vo prvite 7 sekundi od igrata
            drawBonusApple(brown_apple, BROWN, DARKBROWN)
        drawApple(apple)
        score = len(wormCoords)-3+bonus # baranje 2: presmetka na score
        drawScore(score) # baranje 2: nova presmetka za poeni
        pygame.display.update()
        FPSCLOCK.tick(FPS)


def drawPressKeyMsg():
    pressKeySurf = BASICFONT.render('Press a key to play.', True, DARKGRAY)
    pressKeyRect = pressKeySurf.get_rect()
    pressKeyRect.topleft = (WINDOWWIDTH - 200, WINDOWHEIGHT - 30)
    DISPLAYSURF.blit(pressKeySurf, pressKeyRect)


def checkForKeyPress():
    if len(pygame.event.get(QUIT)) > 0:
        terminate()

    keyUpEvents = pygame.event.get(KEYUP)
    if len(keyUpEvents) == 0:
        return None
    if keyUpEvents[0].key == K_ESCAPE:
        terminate()
    return keyUpEvents[0].key


def showStartScreen():
    titleFont = pygame.font.Font('freesansbold.ttf', 100)
    titleSurf1 = titleFont.render('Wormy!', True, WHITE, DARKGREEN)
    titleSurf2 = titleFont.render('Wormy!', True, GREEN)

    degrees1 = 0
    degrees2 = 0
    while True:
        DISPLAYSURF.fill(BGCOLOR)
        rotatedSurf1 = pygame.transform.rotate(titleSurf1, degrees1)
        rotatedRect1 = rotatedSurf1.get_rect()
        rotatedRect1.center = (WINDOWWIDTH / 2, WINDOWHEIGHT / 2)
        DISPLAYSURF.blit(rotatedSurf1, rotatedRect1)

        rotatedSurf2 = pygame.transform.rotate(titleSurf2, degrees2)
        rotatedRect2 = rotatedSurf2.get_rect()
        rotatedRect2.center = (WINDOWWIDTH / 2, WINDOWHEIGHT / 2)
        DISPLAYSURF.blit(rotatedSurf2, rotatedRect2)

        drawPressKeyMsg()

        if checkForKeyPress():
            pygame.event.get()  # clear event queue
            return
        pygame.display.update()
        FPSCLOCK.tick(FPS)
        degrees1 += 3  # rotate by 3 degrees each frame
        degrees2 += 7  # rotate by 7 degrees each frame


def terminate():
    pygame.quit()
    sys.exit()


def getRandomLocation():
    return {'x': random.randint(0, CELLWIDTH - 1), 'y': random.randint(0, CELLHEIGHT - 1)}


def showGameOverScreen():
    gameOverFont = pygame.font.Font('freesansbold.ttf', 150)
    gameSurf = gameOverFont.render('Game', True, WHITE)
    overSurf = gameOverFont.render('Over', True, WHITE)
    gameRect = gameSurf.get_rect()
    overRect = overSurf.get_rect()
    gameRect.midtop = (WINDOWWIDTH / 2, 10)
    overRect.midtop = (WINDOWWIDTH / 2, gameRect.height + 10 + 25)

    DISPLAYSURF.blit(gameSurf, gameRect)
    DISPLAYSURF.blit(overSurf, overRect)

    # baranje 2:
    scoreFont = pygame.font.Font('freesansbold.ttf', 16)
    scoreSurf = scoreFont.render('Score: ' + str(score), True, WHITE)
    scoreRect = scoreSurf.get_rect()
    scoreRect.midtop = (WINDOWWIDTH / 2, 450)
    DISPLAYSURF.blit(scoreSurf, scoreRect)

    # baranje 3: ednostavna kreacija na kopcinjata
    buttonFont = pygame.font.Font('freesansbold.ttf', 30)
    startSurf = buttonFont.render('Start from the beggining', True, PINK, BGCOLOR)
    quitSurf = buttonFont.render('Quit', True, DARKPINK, BGCOLOR)
    startRect = startSurf.get_rect()
    quitRect = quitSurf.get_rect()
    startRect.midtop = (WINDOWWIDTH / 2, 350)
    quitRect.midtop = (WINDOWWIDTH / 2, 400)
    DISPLAYSURF.blit(startSurf, startRect)
    DISPLAYSURF.blit(quitSurf, quitRect)

    drawPressKeyMsg()
    pygame.display.update()
    pygame.time.wait(500)
    checkForKeyPress()  # clear out any key presses in the event queue

    while True:
        if checkForKeyPress():
            pygame.event.get()  # clear event queue
            return
        # baranje 3: proverka dali se pritisnati kopcinjata
        for event in pygame.event.get():
            if event.type == MOUSEBUTTONDOWN:
                if startRect.collidepoint(pygame.mouse.get_pos()):
                    return
                if quitRect.collidepoint(pygame.mouse.get_pos()):
                    terminate()


def drawScore(score):
    scoreSurf = BASICFONT.render('Score: %s' % (score), True, WHITE)
    scoreRect = scoreSurf.get_rect()
    scoreRect.topleft = (WINDOWWIDTH - 120, 10)
    DISPLAYSURF.blit(scoreSurf, scoreRect)


def drawWorm(wormCoords, color, darkcolor):
    for coord in wormCoords:
        x = coord['x'] * CELLSIZE
        y = coord['y'] * CELLSIZE
        wormSegmentRect = pygame.Rect(x, y, CELLSIZE, CELLSIZE)
        pygame.draw.rect(DISPLAYSURF, darkcolor, wormSegmentRect)
        wormInnerSegmentRect = pygame.Rect(x + 4, y + 4, CELLSIZE - 8, CELLSIZE - 8)
        pygame.draw.rect(DISPLAYSURF, color, wormInnerSegmentRect)


def drawApple(coord):
    x = coord['x'] * CELLSIZE
    y = coord['y'] * CELLSIZE
    appleRect = pygame.Rect(x, y, CELLSIZE, CELLSIZE)
    pygame.draw.rect(DISPLAYSURF, RED, appleRect)


def drawGrid():
    for x in range(0, WINDOWWIDTH, CELLSIZE):  # draw vertical lines
        pygame.draw.line(DISPLAYSURF, DARKGRAY, (x, 0), (x, WINDOWHEIGHT))
    for y in range(0, WINDOWHEIGHT, CELLSIZE):  # draw horizontal lines
        pygame.draw.line(DISPLAYSURF, DARKGRAY, (0, y), (WINDOWWIDTH, y))


if __name__ == '__main__':
    main()
