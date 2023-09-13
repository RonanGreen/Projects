
* Title      : Endless Runner 
* Written by : Ronan Green
* Date       : 08/03/2023
* Description: Endless Runner 
*-----------------------------------------------------------
    ORG    $1000
START:                  ; first instruction of program

*-----------------------------------------------------------
* Section       : Trap Codes
* Description   : Trap Codes used throughout StarterKit
*-----------------------------------------------------------
* Trap CODES
TC_SCREEN   EQU         33          ; Screen size information trap code
TC_S_SIZE   EQU         00          ; Places 0 in D1.L to retrieve Screen width and height in D1.L
                                    ; First 16 bit Word is screen Width and Second 16 bits is screen Height
TC_KEYCODE  EQU         19          ; Check for pressed keys
TC_DBL_BUF  EQU         92          ; Double Buffer Screen Trap Code
TC_CURSR_P  EQU         11          ; Trap code cursor position

TC_EXIT     EQU         09          ; Exit Trapcode

*-----------------------------------------------------------
* Section       : Charater Setup
* Description   : Size of Player and Enemy and properties
* of these characters e.g Starting Positions and Sizes
*-----------------------------------------------------------
PLYR_W_INIT EQU         14          ; Players initial Width
PLYR_H_INIT EQU         14         ; Players initial Height

PLYR_DFLT_V EQU         00          ; Default Player Velocity
PLYR_JUMP_V EQU        -20          ; Player Jump Velocity
PLYR_DFLT_G EQU         01          ; Player Default Gravity

GND_TRUE    EQU         01          ; Player on Ground True
GND_FALSE   EQU         00          ; Player on Ground False

RUN_INDEX   EQU         00          ; Player Run Sound Index  
JMP_INDEX   EQU         01          ; Player Jump Sound Index  
OPPS_INDEX  EQU         02          ; Player Opps Sound Index

ENMY_W_INIT EQU         14          ; Enemy initial Width
ENMY_H_INIT EQU         14         ; Enemy initial Height

*-----------------------------------------------------------
* Section       : Game Stats
* Description   : Points
*-----------------------------------------------------------
POINTS      EQU         01          ; Points added

*-----------------------------------------------------------
* Section       : Keyboard Keys
* Description   : Spacebar and Escape or two functioning keys
* Spacebar to JUMP and Escape to Exit Game
*-----------------------------------------------------------
SPACEBAR    EQU         $20         ; Spacebar ASCII Keycode
ESCAPE      EQU         $1B         ; Escape ASCII Keycode

*-----------------------------------------------------------
* Subroutine    : Initialise
* Description   : Initialise game data into memory such as 
* sounds and screen size
*-----------------------------------------------------------
INTRO:
    CLR.L   D0
    CLR.L   D1
    
    MOVE.W  #$FF00,     D1          
    MOVE.L  #11,        D0
    TRAP #15
    
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$1505,     D1          ; Col 20, Row 1
    TRAP    #15                     ; Trap (Perform action)
    LEA     CTRL_MSG, A1         ; Keycode
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15   
    
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$1510,     D1          ; Col 20, Row 1
    TRAP    #15                     ; Trap (Perform action)
    LEA     INTRO_MSG, A1         ; Keycode
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)
    
    CLR.L   D0
    CLR.L   D1
    
    MOVE.B  #5, D0 ;Read a single character from the keyboard
    TRAP    #15
    CMP.L   #69, D0
    BEQ     OVER
    BRA     INITIALISE
    
INITIALISE:
    
    ; Initialise Sounds
    BSR     RUN_LOAD                ; Load Run Sound into Memory
    BSR     JUMP_LOAD               ; Load Jump Sound into Memory
    BSR     OPPS_LOAD               ; Load Opps (Collision) Sound into Memory

    ; Screen Size
    MOVE.B  #TC_SCREEN, D0          ; access screen information
    MOVE.L  #TC_S_SIZE, D1          ; placing 0 in D1 triggers loading screen size information
    TRAP    #15                     ; interpret D0 and D1 for screen size
    MOVE.W  D1,         SCREEN_H    ; place screen height in memory location
    SWAP    D1                      ; Swap top and bottom word to retrive screen size
    MOVE.W  D1,         SCREEN_W    ; place screen width in memory location

    ; Place the Player at the center of the screen
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_W,   D1          ; Place Screen width in D1
    DIVU    #04,        D1          ; divide by 2 for center on X Axis
    MOVE.L  D1,         PLAYER_X    ; Players X Position

    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_H,   D1          ; Place Screen width in D1
    DIVU    #02,        D1          ; divide by 2 for center on Y Axis
    MOVE.L  D1,         PLAYER_Y    ; Players Y Position

    ; Initialise Player Score2
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.L  #00,        D1          ; Init Score
    MOVE.L  D1,         PLAYER_SCORE
    
    ;Initalise health
    CLR.L   D1
    MOVE.L  #3,    D1
    MOVE.L  D1,      HEALTH_NUM
    
    CLR.L   D1
    MOVE.L  #00,    D1
    MOVE.L  D1,     SCORE


    ; Initial Position for Enemy
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_W,   D1          ; Place Screen width in D1
    MOVE.L  D1,         ENEMY_X     ; Enemy X Position

    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_H,   D1          ; Place Screen width in D1
    DIVU    #02,        D1          ; divide by 2 for center on Y Axis
    MOVE.L  D1,         ENEMY_Y     ; Enemy Y Position

    ; Enable the screen back buffer(see easy 68k help)
	MOVE.B  #TC_DBL_BUF,D0          ; 92 Enables Double Buffer
    MOVE.B  #17,        D1          ; Combine Tasks
	TRAP	#15                     ; Trap (Perform action)

    ; Clear the screen (see easy 68k help)
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
	MOVE.W  #$FF00,     D1          ; Fill Screen Clear
	TRAP	#15                     ; Trap (Perform action)



    ; Enable the screen back buffer(see easy 68k help)
	MOVE.B  #TC_DBL_BUF,D0          ; 92 Enables Double Buffer
    MOVE.B  #17,        D1          ; Combine Tasks
	TRAP	#15                     ; Trap (Perform action)

    ; Clear the screen (see easy 68k help)
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
	MOVE.W  #$FF00,     D1          ; Fill Screen Clear
	TRAP	#15                     ; Trap (Perform action)

*-----------------------------------------------------------
* Subroutine    : Game
* Description   : Game including main GameLoop. GameLoop is like
* a while loop in that it runs forever until interupted
* (Input, Update, Draw). The Enemies Run at Player Jump to Avoid
*-----------------------------------------------------------
GAME:
    BSR     PLAY_RUN                ; Play Run Wav
    

GAMELOOP:
        ; Main Gameloop
    BSR     INPUT                   ; Check Keyboard Input
    BSR     UPDATE                  ; Update positions and points
    BSR     CHECK_COLLISIONS        ; Check for Collisions
    BSR     DRAW                    ; Draw the Scene
    BRA     GAMELOOP                ; Loop back to GameLoop

*-----------------------------------------------------------
* Subroutine    : Input
* Description   : Process Keyboard Input
*-----------------------------------------------------------
INPUT:
    ; Process Input
    CLR.L   D1                      ; Clear Data Register
    MOVE.B  #TC_KEYCODE,D0          ; Listen for Keys
    TRAP    #15                     ; Trap (Perform action)
    MOVE.B  D1,         D2          ; Move last key D1 to D2
    CMP.B   #00,        D2          ; Key is pressed
    BEQ     PROCESS_INPUT           ; Process Key
    TRAP    #15                     ; Trap for Last Key
    ; Check if key still pressed
    CMP.B   #$FF,       D1          ; Is it still pressed
    BEQ     PROCESS_INPUT           ; Process Last Key
    RTS                             ; Return to subroutine

*-----------------------------------------------------------
* Subroutine    : Process Input
* Description   : Branch based on keys pressed
*-----------------------------------------------------------
PROCESS_INPUT:
    MOVE.L  D2,         CURRENT_KEY ; Put Current Key in Memory
    CMP.L   #ESCAPE,    CURRENT_KEY ; Is Current Key Escape
    BEQ     EXIT                    ; Exit if Escape
    CMP.L   #38,  CURRENT_KEY ; Is Current Key uparrow
    BEQ     SET_ON_ROOF                  ; Jump
    CMP.L   #40,  CURRENT_KEY ; Is Current Key downarrow
    BEQ     SET_ON_GROUND                  ; Jump
    RTS                             ; Return to subroutine

*-----------------------------------------------------------
* Subroutine    : Update
* Description   : Main update loop update Player and Enemies
*-----------------------------------------------------------
UPDATE:
    ; Update the Players Positon based on Velocity and Gravity

    
    

    ; Move the Enemy
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    CLR.L   D1                      ; Clear the contents of D0
    MOVE.L  ENEMY_X,    D1          ; Move the Enemy X Position to D0
    CMP.L   #00,        D1
    BLE     RESET_ENEMY_POSITION    ; Reset Enemy if off Screen
    BRA     MOVE_ENEMY              ; Move the Enemy

    RTS                             ; Return to subroutine  

*-----------------------------------------------------------
* Subroutine    : Move Enemy
* Description   : Move Enemy Right to Left
*-----------------------------------------------------------
MOVE_ENEMY:
    SUB.L   #02,        ENEMY_X     ; Move enemy by X Value
    RTS

*-----------------------------------------------------------
* Subroutine    : Reset Enemy
* Description   : Reset Enemy if to passes 0 to Right of Screen
*-----------------------------------------------------------
RESET_ENEMY_POSITION:
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_W,   D1          ; Place Screen width in D1
    MOVE.L  D1,         ENEMY_X     ; Enemy X Position
    
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_H,   D1          ; Place Screen width in D1
    DIVU    #02,        D1          ; divide by 2 for center on Y Axis
    MOVE.L  D1,         ENEMY_Y     ; Enemy Y Position
    
    MOVE.B #8, D0 #Loads D1 with Time in 100's of Seconds since midnight (6 Bits)

    TRAP #15  #Place time in D1

    AND.L #$0000FFFF, D1 # AND 6 Bits to prevent any overflow

    DIVU #02, D1 #Divide by 10, 100, 1000, 10000 depending on Number range required

    SWAP D1 #SWAP Higher Order Word and Lower Order Word in D1

    ADDQ.W #1, D1 #Add 1 to D1.W so number is at least 1

    MOVE.W D1, D2 #Extract the number from D1.W

    CLR.L D1 # Clear contents of D1

    MOVE.W D2, D1 #Move the generated number to D1
    
    DIVS   #02,D1
    
    CMP.L  #01,D1
    BEQ    DONE
    BRA    HIGH
HIGH   
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_H,   D1          ; Place Screen width in D1
    DIVU    #04,        D1          ; divide by 2 for center on Y Axis
    MOVE.L  D1,         ENEMY_Y     ; Enemy Y Position
    
DONE
    RTS

*-----------------------------------------------------------
* Subroutine    : Draw
* Description   : Draw Screen
*-----------------------------------------------------------
DRAW: 
    ; Enable back buffer
    MOVE.B  #94,        D0
    TRAP    #15

    ; Clear the screen
    MOVE.B	#TC_CURSR_P,D0          ; Set Cursor Position
	MOVE.W	#$FF00,     D1          ; Clear contents
	TRAP    #15                     ; Trap (Perform action)

    BSR     GROUND
    BSR     DRAW_PLYR_DATA          ; Draw Draw Score, HUD, Player X and Y
    BSR     DRAW_PLAYER             ; Draw Player
    BSR     DRAW_ENEMY              ; Draw Enemy
    RTS                             ; Return to subroutine

*-----------------------------------------------------------
* Subroutine    : Draw Player Data
* Description   : Draw Player X, Y, Velocity, Gravity and OnGround
*-----------------------------------------------------------

DRAW_PLYR_DATA:
    
    
    
    ; Player Score Message
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$0201,     D1          ; Col 02, Row 01
    TRAP    #15                     ; Trap (Perform action)
    LEA     SCORE_MSG,  A1          ; Score Message
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)

    ; Player Score Value
    CMP.L   #00, PLAYER_SCORE
    BEQ CON
    BRA ADD
ADD
    ADD.L   #01,    SCORE
    MOVE.L  #00,    PLAYER_SCORE
    BRA CON
CON
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$0901,     D1          ; Col 09, Row 01
    TRAP    #15                     ; Trap (Perform action)
    MOVE.B  #03,        D0          ; Display number at D1.L
    MOVE.L  SCORE,D1         ; Move Score to D1.L
    TRAP    #15                     ; Trap (Perform action)
    
    ; Player X Message
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$0202,     D1          ; Col 02, Row 02
    TRAP    #15                     ; Trap (Perform action)
    LEA     X_MSG,      A1          ; X Message
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)
    
    ; Player X
    MOVE.B  #TC_CURSR_P, D0          ; Set Cursor Position
    MOVE.W  #$0502,     D1          ; Col 05, Row 02
    TRAP    #15                     ; Trap (Perform action)
    MOVE.B  #03,        D0          ; Display number at D1.L
    MOVE.L  PLAYER_X,   D1          ; Move X to D1.L
    TRAP    #15                     ; Trap (Perform action)
    
    ; Player Y Message
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$1002,     D1          ; Col 10, Row 02
    TRAP    #15                     ; Trap (Perform action)
    LEA     Y_MSG,      A1          ; Y Message
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)
    
    ; Player Y
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$1202,     D1          ; Col 12, Row 02
    TRAP    #15                     ; Trap (Perform action)
    MOVE.B  #03,        D0          ; Display number at D1.L
    MOVE.L  PLAYER_Y,   D1          ; Move X to D1.L
    TRAP    #15                     ; Trap (Perform action) 
    
    ;HEALTH MESSAGE
    MOVE.B  #TC_CURSR_P,D0
    MOVE.W  #$0304, D1
    TRAP    #15
    LEA     HEALTH_MSG, A1
    MOVE.L  #13,    D0
    TRAP    #15
    
    ;HEALTH
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$0D04,     D1          ; Col 12, Row 02
    TRAP    #15                     ; Trap (Perform action)
    MOVE.B  #03,        D0          ; Display number at D1.L
    MOVE.L  HEALTH_NUM,   D1          ; Move X to D1.L
    TRAP    #15                     ; Trap (Perform action) 


    
    ; Show Keys Pressed
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$2001,     D1          ; Col 20, Row 1
    TRAP    #15                     ; Trap (Perform action)
    LEA     KEYCODE_MSG, A1         ; Keycode
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)

    ; Show KeyCode
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$3001,     D1          ; Col 30, Row 1
    TRAP    #15                     ; Trap (Perform action)    
    MOVE.L  CURRENT_KEY,D1          ; Move Key Pressed to D1
    MOVE.B  #03,        D0          ; Display the contents of D1
    TRAP    #15                     ; Trap (Perform action)

    RTS  
    




*-----------------------------------------------------------
* Subroutine    : On Ground
* Description   : Set the Player On Ground
*-----------------------------------------------------------
SET_ON_GROUND:
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_H,   D1          ; Place Screen width in D1
    DIVU    #02,        D1          ; divide by 2 for center on Y Axis
    MOVE.L  D1,         PLAYER_Y    ; Reset the Player Y Position
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    RTS
    
    

*-----------------------------------------------------------
* Subroutine    : Off Ground
* Description   : Set the Player Off Ground
*-----------------------------------------------------------
SET_ON_ROOF
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    MOVE.W  SCREEN_H,   D1          ; Place Screen width in D1
    DIVU    #04,        D1          ; divide by 2 for center on Y Axis
    MOVE.L  D1,         PLAYER_Y    ; Reset the Player Y Position
    CLR.L   D1                      ; Clear contents of D1 (XOR is faster)
    RTS



*-----------------------------------------------------------
* Subroutines   : Sound Load and Play
* Description   : Initialise game sounds into memory 
* Current Sounds are RUN, JUMP and Opps for Collision
*-----------------------------------------------------------
RUN_LOAD:
    LEA     RUN_WAV,    A1          ; Load Wav File into A1
    MOVE    #RUN_INDEX, D1          ; Assign it INDEX
    MOVE    #71,        D0          ; Load into memory
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

PLAY_RUN:
    MOVE    #RUN_INDEX, D1          ; Load Sound INDEX
    MOVE    #72,        D0          ; Play Sound
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

JUMP_LOAD:
    LEA     JUMP_WAV,   A1          ; Load Wav File into A1
    MOVE    #JMP_INDEX, D1          ; Assign it INDEX
    MOVE    #71,        D0          ; Load into memory
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

PLAY_JUMP:
    MOVE    #JMP_INDEX, D1          ; Load Sound INDEX
    MOVE    #72,        D0          ; Play Sound
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

OPPS_LOAD:
    LEA     OPPS_WAV,   A1          ; Load Wav File into A1
    MOVE    #OPPS_INDEX,D1          ; Assign it INDEX
    MOVE    #71,        D0          ; Load into memory
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

PLAY_OPPS:
    MOVE    #OPPS_INDEX,D1          ; Load Sound INDEX
    MOVE    #72,        D0          ; Play Sound
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

*-----------------------------------------------------------
* Subroutine    : Draw Player and ground
* Description   : Draw Player Square
*-----------------------------------------------------------
GROUND:
    MOVE.L  #WHITE,     D1          ; Set Background color
    MOVE.B  #80,        D0          ; Task for Background Color
    TRAP    #15                     ; Trap (Perform action)
    
    MOVE.W  SCREEN_W, D1
    MOVE.W  SCREEN_H, D2
    DIVU    #02,    D2
    ADD     #015,    D2
    MOVE.W  #0,  D3
    MOVE.W  D2, D4
    MOVE.B  #84, D0
    TRAP    #15
    
    MOVE.W  SCREEN_W, D1
    MOVE.W  SCREEN_H, D2
    DIVU    #04,    D2
    SUB     #01,    D2
    MOVE.W  #0,  D3
    MOVE.W  D2, D4
    MOVE.B  #84, D0
    TRAP    #15
 

DRAW_PLAYER:
    ; Set Pixel Colors
    MOVE.L  #GREEN,     D1          ; Set Background color
    MOVE.B  #80,        D0          ; Task for Background Color
    TRAP    #15                     ; Trap (Perform action)

    ; Set X, Y, Width and Height
    MOVE.L  PLAYER_X,   D1          ; X
    MOVE.L  PLAYER_Y,   D2          ; Y
    MOVE.L  PLAYER_X,   D3
    ADD.L   #PLYR_W_INIT,   D3      ; Width
    MOVE.L  PLAYER_Y,   D4 
    ADD.L   #PLYR_H_INIT,   D4      ; Height
    
    ; Draw Player
    MOVE.B  #87,        D0          ; Draw Player
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

*-----------------------------------------------------------
* Subroutine    : Draw Enemy
* Description   : Draw Enemy Square
*-----------------------------------------------------------
DRAW_ENEMY:
    ; Set Pixel Colors
    MOVE.L  #WHITE,       D1          ; Set Background color
    MOVE.B  #80,        D0          ; Task for Background Color
    TRAP    #15                     ; Trap (Perform action)

    ; Set X, Y, Width and Height
    MOVE.L  ENEMY_X,    D1          ; X
    MOVE.L  ENEMY_Y,    D2          ; Y
    MOVE.L  ENEMY_X,    D3
    ADD.L   #ENMY_W_INIT,   D3      ; Width
    MOVE.L  ENEMY_Y,    D4 
    ADD.L   #ENMY_H_INIT,   D4      ; Height
    
    ; Draw Enemy    
    MOVE.B  #87,        D0          ; Draw Enemy
    TRAP    #15                     ; Trap (Perform action)
    RTS                             ; Return to subroutine

*-----------------------------------------------------------
* Subroutine    : Collision Check
* Description   : Axis-Aligned Bounding Box Collision Detection
* Algorithm checks for overlap on the 4 sides of the Player and 
* Enemy rectangles
* PLAYER_X <= ENEMY_X + ENEMY_W &&
* PLAYER_X + PLAYER_W >= ENEMY_X &&
* PLAYER_Y <= ENEMY_Y + ENEMY_H &&
* PLAYER_H + PLAYER_Y >= ENEMY_Y
*-----------------------------------------------------------
CHECK_COLLISIONS:
    CLR.L   D1                      ; Clear D1
    CLR.L   D2                      ; Clear D2
    
    ; Enable back buffer
    MOVE.B  #94,        D0
    TRAP    #15
PLAYER_X_LTE_TO_ENEMY_X_PLUS_W:
    MOVE.L  PLAYER_X,   D1          ; Move Player X to D1
    MOVE.L  ENEMY_X,    D2          ; Move Enemy X to D2
    ADD.L   ENMY_W_INIT,D2          ; Set Enemy width X + Width
    CMP.L   D1,         D2          ; Do the Overlap ?
    BLE     PLAYER_X_PLUS_W_LTE_TO_ENEMY_X  ; Less than or Equal ?
    BRA     COLLISION_CHECK_DONE    ; If not no collision
PLAYER_X_PLUS_W_LTE_TO_ENEMY_X:     ; Check player is not  
    ADD.L   PLYR_W_INIT,D1          ; Move Player Width to D1
    MOVE.L  ENEMY_X,    D2          ; Move Enemy X to D2
    CMP.L   D1,         D2          ; Do they OverLap ?
    BGE     PLAYER_Y_LTE_TO_ENEMY_Y_PLUS_H  ; Less than or Equal
    BRA     COLLISION_CHECK_DONE    ; If not no collision   
PLAYER_Y_LTE_TO_ENEMY_Y_PLUS_H:     
    MOVE.L  PLAYER_Y,   D1          ; Move Player Y to D1
    MOVE.L  ENEMY_Y,    D2          ; Move Enemy Y to D2
    ADD.L   ENMY_H_INIT,D2          ; Set Enemy Height to D2
    CMP.L   D1,         D2          ; Do they Overlap ?
    BLE     PLAYER_Y_PLUS_H_LTE_TO_ENEMY_Y  ; Less than or Equal
    BRA     COLLISION_CHECK_DONE    ; If not no collision 
PLAYER_Y_PLUS_H_LTE_TO_ENEMY_Y:     ; Less than or Equal ?
    ADD.L   PLYR_H_INIT,D1          ; Add Player Height to D1
    MOVE.L  ENEMY_Y,    D2          ; Move Enemy Height to D2  
    CMP.L   D1,         D2          ; Do they OverLap ?
    BGE     COLLISION               ; Collision !
    BRA     COLLISION_CHECK_DONE    ; If not no collision
COLLISION_CHECK_DONE:               ; No Collision Update points
    MOVE.L  PLAYER_X,   D1          ; Move Player X to D1
    MOVE.L  ENEMY_X,    D2          ; Move Enemy X to D2
    CMP.L   D1,D2
    BEQ     ADD_POINT 
    RTS
    
ADD_POINT
    ADD     #01,    PLAYER_SCORE
    RTS                             ; Return to subroutine

COLLISION:
    BSR     PLAY_OPPS               ; Play Opps Wav
    MOVE.L  #00, PLAYER_SCORE       ; Reset Player Score
    SUB.L   #01, HEALTH_NUM
    CMP.L   #00, HEALTH_NUM
    BEQ     EXIT
    RTS                             ; Return to subroutine

*-----------------------------------------------------------
* Subroutine    : EXIT
* Description   : Exit message and End Game
*-----------------------------------------------------------

       
    
    
    
    

EXIT:


    CLR.L   D0
    CLR.L   D1
    
    MOVE.W  #$FF00,     D1          
    MOVE.L  #11,        D0
    TRAP #15
    
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$1515,     D1          ; Col 20, Row 1
    TRAP    #15                     ; Trap (Perform action)
    LEA     INTRO_MSG, A1         ; Keycode
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)
        
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$2008,     D1          ; Col 20, Row 1
    TRAP    #15                     ; Trap (Perform action)
    LEA     GAMEOVER_MSG, A1         ; Keycode
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)
    
    ; Player Score Message
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$2010,     D1          ; Col 02, Row 01
    TRAP    #15                     ; Trap (Perform action)
    LEA     SCORE_MSG,  A1          ; Score Message
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)

    ; Player Score Value
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$2610,     D1          ; Col 09, Row 01
    TRAP    #15                     ; Trap (Perform action)
    MOVE.B  #03,        D0          ; Display number at D1.L
    MOVE.L  SCORE,D1         ; Move Score to D1.L
    TRAP    #15                     ; Trap (Perform action)
    
    CLR.L   D0
    CLR.L   D1
    
    MOVE.B  #5, D0 ;Read a single character from the keyboard
    TRAP    #15
    CMP.L   #ESCAPE, D1                   ; Trap (Perform action)
    BEQ     OVER
    BRA     INTRO    

OVER: 
    MOVE.B  #TC_CURSR_P,D0          ; Set Cursor Position
    MOVE.W  #$1515,     D1          ; Col 20, Row 1
    TRAP    #15                     ; Trap (Perform action)
    LEA     END_MSG, A1         ; Keycode
    MOVE    #13,        D0          ; No Line feed
    TRAP    #15                     ; Trap (Perform action)  
    
    SIMHALT

*-*-----------------------------------------------------------
* Section       : Messages
* Description   : Messages to Print on Console, names should be
* self documenting
*-----------------------------------------------------------
SCORE_MSG       DC.B    'Score: ', 0       ; Score Message
KEYCODE_MSG     DC.B    'KeyCode : ', 0     ; Keycode Message
JUMP_MSG        DC.B    'Jump....', 0       ; Jump Message
HEALTH_MSG      DC.B    'HEALTH...', 0
INTRO_MSG       DC.B    'PRESS A KEY TO START OR ESCAPE TO EXIT', 0
GAMEOVER_MSG    DC.B    'GAME OVER', 0
END_MSG         DC.B    'THANKS FOR PLAYING', 0
CTRL_MSG        DC.B    'UP ARROW AND DOWN ARROWS TO CONTROL', 0

X_MSG           DC.B    'X:', 0             ; X Position Message
Y_MSG           DC.B    'Y:', 0             ; Y Position Message


EXIT_MSG        DC.B    'GAME OVER', 0    ; Exit Message

*-----------------------------------------------------------
* Section       : Graphic Colors
* Description   : Screen Pixel Color
*-----------------------------------------------------------
WHITE           EQU     $00FFFFFF
RED             EQU     $00FFC527
GREEN           EQU     $0022FF00

*-----------------------------------------------------------
* Section       : Screen Size
* Description   : Screen Width and Height
*-----------------------------------------------------------
SCREEN_W        DS.W    01  ; Reserve Space for Screen Width
SCREEN_H        DS.W    01  ; Reserve Space for Screen Height

*-----------------------------------------------------------
* Section       : Keyboard Input
* Description   : Used for storing Keypresses
*-----------------------------------------------------------
CURRENT_KEY     DS.L    01  ; Reserve Space for Current Key Pressed

*-----------------------------------------------------------
* Section       : Character Positions
* Description   : Player and Enemy Position Memory Locations
*-----------------------------------------------------------
PLAYER_X        DS.L    01  ; Reserve Space for Player X Position
PLAYER_Y        DS.L    01  ; Reserve Space for Player Y Position
PLAYER_SCORE    DS.L    01  ; Reserve Space for Player Score
SCORE           DS.L    01
HEALTH_NUM      DS.L    01  


ENEMY_X         DS.L    01  ; Reserve Space for Enemy X Position
ENEMY_Y         DS.L    01  ; Reserve Space for Enemy Y Position

*-----------------------------------------------------------
* Section       : Sounds
* Description   : Sound files, which are then loaded and given
* an address in memory, they take a longtime to process and play
* so keep the files small. Used https://voicemaker.in/ to 
* generate and Audacity to convert MP3 to WAV
*-----------------------------------------------------------
JUMP_WAV        DC.B    'jump.wav',0        ; Jump Sound
RUN_WAV         DC.B    'run.wav',0         ; Run Sound
OPPS_WAV        DC.B    'opps.wav',0        ; Collision Opps

    END    START        ; last line of source



*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
