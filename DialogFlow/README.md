# Google DialogFlow integration

This skill exemplifies integration with DialogFlow. The skill uses a Single User Engement Policy, which means that only one user can interact at a time.
When a user approaches, Furhat will initiate the interaction by triggering the Welcome intent in the DialogFlow agent.
The interaction will end if either the user is leavíng, or if an intent is marked as "end of conversation" in DialogFlow.

## Setup

Before you can run the skill, you need to connect it to your DialogFlow agent:

1. Follow the instructions for enabling the API in DialogFlow:
    [https://cloud.google.com/dialogflow/docs/quick/setup]
1. Take note of your project id
2. Create and download a credentials json file for your project:
    [https://dialogflow.com/docs/reference/v2-auth-setup]

The project id and a pointer to your credentials file should be provided to the DialogFlow constructor in main.kt

## Dry run

You can check that the integration works, by chatting directly with your agent through the console. Run consoleTest.kt. If this works, you should be able to run the skill.

## Adding gestures to responses

As the flow is implemented now, you can also add gestures to your responses by adding a custom payload in DialogFlow, like this:

```
{"gesture": "smile"}
```

## Adding a GUI

TBA: You could potentially use the Google Assistant responses to add Cards, etc, and show them on the Furhat GUI.