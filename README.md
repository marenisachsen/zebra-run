# zebra - run

The educational requirements of this project are made so the group will learn all the phases of developing a software architecture through game development. This includes design, evaluation, implementation, testing, and iterations.

The group has decided to create a turn based online multiplayer game with a focus on the quality attributes modifiability, usability and interoperability in both the design and implementation. Well known architectural principles will be utilized to achieve this goal.

## Innhold
1. [Game Concept](#Game_concept)
2. [Version control: git](#git)
    1. [The main branches ](#main)
    2. [Supporting branches ](#sub)
3. [Technology](#Technology)
    1. [LibGDX](#LibGDX)
    2. [Android Studio](#Android)
    3. [Database](#Database)
4. [Installing](#Installing)
    1. [Set up android studio for Java](#SetupAndroid)
5. [Views](#Views)
    1. [Logical view](#logical)
    2. [Process view](#process)
    3. [Development view](#development)



## Game Concept <a name="Game_concept"></a>

The character is a zebra, who is constantly running, similar to other running games. Randomly generated obstacles, like clouds, bushes and tree stumps,  will be spawn in the path. To stay alive the zebra has to avoid these obstacles. The player starts with three lives, each time the zebra collides with an obstacle, the player loses a life. The player controls the zebra by tapping the screen. Tapping on the left side of the screen makes the zebra jump, whilst tapping on the right side makes it slide. The goal of the game is to survive as long as possible to achieve the highest score. The longer the player can keep the zebra alive, the higher the score will become.

The game is a multiplayer game, inspired by the well known google chrome game "Dinosaur Game". The game has a turn-based implementation. First, one player starts a new game. When the player has played one round, a code is displayed on the screen. This code can be passed on to others who can create a game with the code and try to beat the score of the initial game creator.

 [Promotation video](https://youtu.be/g_0b8GRzMzY)

<img src="zebraPlay.JPG" width=400 height = 300>
<img src="ZebraSlide.png" width=400 height = 300>



## Version control: Git <a name="git"></a>

The group followed specific procedures and strategies to handle branches and releases, in order to avoid data loss and maintain an agile work progress. This provides a robust framework for managing larger projects.

There are two main branches which record the history of the project. The `master` branch stores the official release history, and the develop branch, `copyMaster`, serves as an integration branch for developing between releases. For each issue to be solved, a corresponding branch is created and delegated to the members working on it. When a possible issue is resolved, the branch will be merged to `copyMaster`. When the release is ready, `copyMaster`is merged to `master`.

### The main branches 
The development model is greatly inspired by existing models out there. The central repository holds two main branches with an infinite lifetime.

#### Master Branch `master`
The `master` branch at origin should be familiar to every Git user. We consider origin/master to be the main branch where the source code of HEAD always reflects a production-ready state.

#### Development Branch `copyMaster`
Parallel to the master branch, another branch called `copyMaster` exists. We consider origin/copyMaster to be the main branch where the source code of HEAD always reflects a state with the latest delivered development changes for the next release. When the source code in the `copyMaster` is ready to be released, all of the changes should be merged into `master`.

### Supporting branches

The development model uses a variety of supporting branches to aid parallel development between team members. Unlike the main branches, these branches always have a limited life time, since they only exists for the development tasks. The supporting branches is based on and named after each individual issue. When the branch is stable and ready to be merged, another groupmember must merge through merge request back into `copyMaster.

## Technology <a name="Technology"></a>

### LibGDX <a name="LibGDX"></a>
[LibGDX](https://libgdx.com) is a relatively low level, free, open source cross platform game development framework written in Java. It offers a setup tool, which automatically creates a project and downloads everything necessary. Whilst it is relatively easy to learn and use, its simplicity may also be a constraint in terms of modifiability tactics, such as coupling. 


### Android Studio <a name="Android"></a>
[Android Studio](https://developer.android.com/studio?gclid=Cj0KCQjw9_mDBhCGARIsAN3PaFMlDENQMFxDn4L06NanixdoVHvIpUfgVyeCecKrYXnv9Az5vMpl3KgaArqdEALw_wcB&gclsrc=aw.ds) is the official Integrated Development Environment (IDE) for Android app development, based on IntelliJ IDEA. On top of IntelliJ's powerful code editor and developer tools, Android Studio offers even more features that enhance your productivity when building Android apps. Each project in Android Studio contains one or more modules with source code files and resource files. The minimum supported Gradle version is 6.5.

### Database <a name="Database"></a>

[Firebase Cloud Firestore](https://firebase.google.com) is used as a database for this application. It is a cloud-hosted service by Google, and the data is stored as JSON. It is a NoSQL database, which adds flexibility and scalability to the application. Firebase Realtime Database lets you store and synchronize data between every connected client in real time. It does not support LibGDX, which restricts the availability of multiplayer mode to Android targets only. 

Example data fram a test game.
<br>
<br>
<img src = "database1.png">
<img src = "database2.png">


## Installing <a name="Installing"></a>


__How to install the project__
- clone git repo with SSH or HTTP from __Project overview__
- open Android Stuio on your computer 
- choose `get from version control`
    - Version control: `Git` 
- add the cloned git repo in the field `URL`
- choose your `Directory`
- `clone`




## Views <a name="Views"></a>
### Logical View <a name="logical"></a>
<img src = "logical_view.png">

### Process View <a name="process"></a>
<img src = "process_view.png">

### Development View <a name="development"></a>
<img src = "development_view.png">






