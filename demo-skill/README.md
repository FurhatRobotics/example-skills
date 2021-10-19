# Furhat DEMO skill

This is an example-skill aiming to:

1. Show developers how they can structure their projects as they grow in size
2. Showcase various functionality of the Furhat robot
3. Invite Furhat owners to use the skill as-is or customized to demo their Furhat robot to other people

## What the skill does

The gist of the skill is that one user is a confederate, having a dialog with Furhat and asking him to do things, but also sometimes talking to other present users.

Furhat will be in one of three modes:

1. Sleeping - his starting mode, where he will listen to wakeup words or if he hears something he finds interesting
2. Active - in dialog
3. Passive - awake and ready to serve, but not taking any initiatives

## Recommended way of starting

We recommend first running the skill and starting with waking Furhat up and followed by asking Furhat what he can do. Once you've ran through the skill a few times, you will have a better chance of understanding the code.

> **Note**: The skill has certain elements that will not work on the Virtual Furhat, and that require our custom Dog mask and texture for the robot to work as intended. These are still included in the skill for developers to learn what is possible, so if you do a demo please avoid or do work-arounds for these.

## Running the skill

1. Clone the repository, `git clone https://github.com/FurhatRobotics/demo-skill`
2. In IntelliJ IDEA, import the skill as a new gradle project (or module if you have an existing project) (use the _from existing source_ alternative and select the _build.gradle_ file)
3. Make sure you have the Furhat SDK development server or a robot running.
4. Run the skill by the main method in the skill's `main.kt` file. If you want to run on a robot, see [follow this section in the docs](https://docs.furhat.io/skills/#running-a-skill-on-a-robot)

## Support and maintenance

We will actively support this skill and make sure it always is in sync with the latest stable Furhat SDK release

If you have any issues, feedback or ideas, feel free to;

1. Create an issue on [Github](https://github.com/FurhatRobotics/demo-skill/issues)
2. Contact us on [support (at) furhatrobotics.com](mailto:support@furhatrobotics.com)
3. Let us know on the community chat channel - [contact us for an invitation](mailto:support@furhatrobotics.com)

## Contributions

Contributions are welcome, especially when it comes to localization to your language. Create a PR! :-)

## License

See the LICENSE file.
