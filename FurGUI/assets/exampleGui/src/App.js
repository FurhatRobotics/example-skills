import React, {Component} from 'react'
import Furhat from 'furhat-gui'
import { Grid, Row, Col } from 'react-bootstrap'
import Button from './Button'
import Input from './Input'

class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
          "speaking": false,
          "buttons": [],
          "inputFields": []
        }
    }

    componentDidMount() {
        // Needed to access "this" inside our callback
        const INSTANCE = this;

        // Connecting to our skill and subscribing to events
        Furhat(function (furhat) {

            // Our DataDelivery event is getting no custom name and hence gets it's full class name as event name.
            furhat.subscribe('furhatos.app.furgui.DataDelivery', function (data) {
                INSTANCE.setState({
                    ...this.state,
                    buttons: data.buttons,
                    inputFields: data.inputFields
                })
            })

            // This event contains to data so we defined it inline in the flow
            furhat.subscribe('SpeechDone', function () {
                INSTANCE.setState({
                    ...this.state,
                    speaking: false
                })
            })

            // Method that we can access outside of this callback, for sending
            INSTANCE.sendEvent = (data) => {
              furhat.send({
                event_name: data.event_name,
                data: data.data
              })
            }
        })
    }

    clickButton = (button) => {
        this.setState({
            ...this.state,
            speaking: true
        })
        this.sendEvent({
          event_name: "ClickButton",
          data: button
        })
    }

    variableSet = (variable, value) => {
        this.setState({
            ...this.state,
            speaking: true
        })
        this.sendEvent({
          event_name: "VariableSet",
          data: {
            variable,
            value
          }
        })
    }

    render() {
      return (
          <Grid>
            <Row>
                <Col sm={12}>
                    <h1>Example GUI</h1>
                </Col>
            </Row>
            <Row>
                <Col sm={6}>
                    <h2>Buttons</h2>
                    { this.state.buttons.map((label) =>
                        <Button key={label} label={label} onClick={this.clickButton} speaking={this.state.speaking}/>
                    )}
                </Col>
                <Col sm={6}>
                    <h2>Input fields</h2>
                    { this.state.inputFields.map((label) =>
                        <Input key={label} label={label} onSave={this.variableSet} speaking={this.state.speaking}/>
                    )}
                </Col>
            </Row>
          </Grid>
        )
    }

}

export default App;
