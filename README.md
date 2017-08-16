# Furhat example-skills

Example skills provided by Furhat Robotics. Contributions welcome from everyone!

# Contents

Skill                 | Description                                 | Concepts showcased
----------------------|---------------------------------------------|------------------------------------------------------
Presentation          | An output-only, localized presentation      | Basic output, localization
Parrot                | A skill that repeats whatever the users says| Basic input/output
DisplayExample        | A skill with a GUI and graphical elements that Furhat can attend | Working with GUIs/displays
Houndify              | A skill that query's a 3rd party API for answers to queries and also can answer other skills queries | 3rd party API, answering skill queries (for example the QuestionsAndAnswers skill)
Questions And Answers | A skill with hardcoded question and answers and the ability to query other skills for answers | Basic input/output, Grammar, external skill queries (for example Houndify)
Social                | A chit-chat skill with question and answers and that eventually tries to get the user to go to the Quiz skill | Advanced input/output, grammar, follow-up questions, launching external skills (Quiz)
Quiz                  | A multi-answer quiz with a content editor for quiz-questions/answers, startable from other skills | Input output, launchable from other skills (Social)

# Running skills

## If you want to run a skill on a Furhat robot:

The skills are preconfigured to run on a Furhat. To install them;

1. Downdload the latest zipped file in the /distr folder of the specific skill
2. Upload the zip in the web interface's **packages** page under **Uploaded packages**.

^ Note: If the skill does not show up in the skills list, restart the Mode. If it still does not show up, review the Console output.

## If you want to run a skill on your developer machine;

1. Clone this repository
2. Start the Furhat dev-server
3. Go to **packages** page and enter the full path of the skill folder in **Development packages**.
4. Restart the mode
5. If you want to develop in Eclipse, please see [our tutorial on Eclipse development](https://docs.myfurhat.com/tutorials/tutorial_eclipse/)

# Documentation

See [docs.myfurhat.com](https://docs.myfurhat.com).
