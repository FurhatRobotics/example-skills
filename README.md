# Furhat example-skills 2nd generation.

Example skills for the Furhat robot, provided by Furhat Robotics. Contributions welcome from everyone, just send a PR!

# Dependencies

* A running Furhat SDK [See furhat.io](https://furhat.io)
* (For the FurGUI): Node.js version >= 7.2.0 and npm >= 3.10.9

# Contents

Skill                 | Description                                 | Concepts showcased
----------------------|---------------------------------------------|------------------------------------------------------
Interviewee | A fully wizarded skill where Furhat is getting interviewed by a journalist | Wizarding
JokeBot | A skill for the robot (does not work on SDK) that tells jokes and uses the user's reaction to assert if it is a good joke. | Gesture Detection, Storage of interaction data, Randomizing interactions
Quiz | An example of a quiz skill | Wizarding
demo-skill | A skill to demo Furhat | Wizarding
CustomASR | How to implement a custom ASR and use extension functions | Audio Feed, Using 3rd party ASR
FortuneTeller | A robot telling people their fortune | Demo of interaction that works well in noisy environments. 
OpenAIAchat | talk to characters powered by GPT-3 | showcase integration with a Large Language Model

See also skills examples from the community! 
Skill                 | By                              | Link
----------------------|---------------------------------------------|------------------------------------------------------
SaharaInterview | Autoura | https://github.com/Autoura/SahraInterview

# Running skills
1. Clone the repository, `git clone https://github.com/FurhatRobotics/example-skills`
2. In IntelliJ IDEA, import each individual skill as a new gradle project (or module if you have an existing project) (use the _from existing source_ alternative and select the _build.gradle_ file)
3. Make sure you have the Furhat SDK development server or a robot running.
4. If the skill has a GUI (currently only the FurGUI skill), run `npm install` in the GUI root folder
5. Run the skill by the main method in the skill's `main.kt` file. If you want to run on a robot, see [this part of the docs](https://docs.furhat.io/skills/#running-a-skill-on-a-robot)

# Documentation
For more info, see [docs.furhat.io](https://docs.furhat.io).
