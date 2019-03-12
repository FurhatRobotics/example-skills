# Furhat example-skills 2nd generation.

Example skills for the Furhat robot, provided by Furhat Robotics. Contributions welcome from everyone, just send a PR!

For an example of a larger Furhat skill, please see the [Demo skill](https://github.com/FurhatRobotics/demo-skill) residing in it's own repository.

# Dependencies

* A running Furhat SDK [See furhat.io](https://furhat.io)
* (For the FurGUI): Node.js version >= 7.2.0 and npm >= 3.10.9

# Contents

Skill                 | Description                                 | Concepts showcased
----------------------|---------------------------------------------|------------------------------------------------------
Presentation | A skill where Furhat presents himself | showcases a listen-loop, a simple wizard button and expressive speech | Speech output, extention methods, listen-loop, wizard button, onTime
FruitSeller | An interactive skill allowing you to order fruits | Speech input, NLU (intents, entities)
WolframAlpha | A simple interactive skill allowing you to ask various questions to the Wolfram Alpha API | Querying external APIs
PizzaOrder | An interactive form-filling skill allowing you to order a pizzas with various toppings and custom delivery times and locations and some additional chit-chat | Form-filling, more advanced NLU
Quiz | A multi-party, interactive quiz-game allowing one or two persons to play a trivia quiz with Furhat | Multiparty, dynamic intents, recognition phrases
FurGUI | A simple skill showcasing how to add a custom Skill GUI built in React with buttons and input fields | Custom GUI, Button and form-input
Interviewee | A fully wizarded skill where Furhat is getting interviewed by a journalist | Wizarding

# Running skills
1. Clone the repository, `git clone https://github.com/FurhatRobotics/example-skills`
2. In IntelliJ IDEA, import each individual skill as a new gradle project (or module if you have an existing project) (use the _from existing source_ alternative and select the _build.gradle_ file)
3. Make sure you have the Furhat SDK development server or a robot running.
4. If the skill has a GUI (currently only the FurGUI skill), run `npm install` in the GUI root folder
5. Run the skill by the main method in the skill's `main.kt` file. If you want to run on a robot, see [this part of the docs](https://docs.furhat.io/skills/#running-a-skill-on-a-robot)

# Documentation
For more info, see [docs.furhat.io](https://docs.furhat.io).
