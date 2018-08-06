# Furhat example-skills 2nd generation.

Example skills provided by Furhat Robotics. Contributions welcome from everyone!

# Contents

Skill                 | Description                                 | Concepts showcased
----------------------|---------------------------------------------|------------------------------------------------------
Presentation | A skill where Furhat presents himself | showcases a listen-loop, a simple wizard button and expressive speech | Speech output, extention methods, listen-loop, wizard button, onTime
Parrot | A simple, interactive skill that repeats the user, with a few nuggets | Speech input, NLU (intents, entities with external enum-files)
FruitSeller | An interactive skill allowing you to order fruits | Speech input, NLU (intents, entities)
WolframAlpha | A simple interactive skill allowing you to ask various questions to the Wolfram Alpha API | Querying external APIs
PizzaOrder | An interactive form-filling skill allowing you to order a pizzas with various toppings and custom delivery times and locations and some additional chit-chat | Form-filling, more advanced NLU

# Running skills
1. Clone the repository
2. In IntelliJ IDEA, import each individual skill as a new gradle project (or module if you have an existing project) (use the _from existing source_ alternative and select the _build.gradle_ file)
3. Make sure you have the SDK development server or a robot running.
4. Run the skill by the main method in the skill's `main.kt` file.

# Documentation
See [docs.furhat.io](https://docs.furhat.io).
