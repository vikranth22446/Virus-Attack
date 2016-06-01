
# APCS-Final-Project
This project was created in APCS Lynbrook HighSchool Class.
##Introduction:
<p1 color = "red">Play as viruses trying to survive and take over
Goal: Infect all the cells and Get the Most Points
If all your viruses and sick cells die you lose
Alternatively if you conquer all the cells and kill all the white cells you win!
</p1>

<br /> During this project we have created a functional game that has
    <ul><li>A functional AI</li></ul>
    <ul><li>A Gui that coordinates all the movement and have Background Move</li></ul>
    <ul><li>A Scoring System to allow for people to play game</li></ul>
    <ul><li>Nicely Abstracted and  Java doced</li></ul>
    <ul><li>Junit Testing all the methods</li></ul>
Our Class diagram:<br />
![alt tag](https://raw.githubusercontent.com/vikranth22446/APCS-Final-Project/master/Virus_Attack/Desigein Diagram.png)
Detailed Design: Interfaces and Abstract Classes

Locatable :
The interface that is used in all objects that are displayed in game,.
Implemented by: Viruses, AntiViruses and Cells. 

Attacker:
The Attacker is an abstract class that holds variables and methods common to Virus and AntiVirus; it includes health increasing and decreasing
Virus and AntiVirus extend this class.

Cell:
The Cell is an abstract class that holds all the basic functions shared across all three cell types
RedCell, WhiteCell, and SickCell extend this class.


