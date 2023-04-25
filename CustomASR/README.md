# Skill
Skill that shows how a customASR could be implemented.

## Description
This Skill used AWS Transcribe as a customASR.
It sends data to AWS and parses the events sent back for usage in a Skill/State.

This example also shows how to use extension functions to make a state look nice and neat.

The Basic state responds to intents, generic speech and silence.

## Usage
Change the API_KEY and API_SECRET in the params object to valid AWS credentials.