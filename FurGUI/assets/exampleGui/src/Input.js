import React, {Component} from "react"
import {
  Button as BootstrapButton,
  FormGroup,
  ControlLabel,
  FormControl
} from 'react-bootstrap';

class Input extends Component {
  constructor(props) {
      super(props)
      this.state = {
         disabled: true,
         value: ""
      };
  }

  isAllowedSubmit = () => {
    return !this.state.disabled && !this.props.speaking
  }

  handleChange = (e) => {
    this.setState({
        value: e.target.value,
        disabled: e.target.value == ""
    });
  }

  handleClick = () => {
    this.save()
  }

  handleEnter = (e) => {
    if (e.key == "Enter") {
      e.preventDefault()
      if (this.isAllowedSubmit()) {
          this.save()
      }
    }
  }

  save = () => {
    this.props.onSave(this.props.label, this.state.value)
  }

  render() {
    let { label } = this.props
    return (
      <div>
          <form>
              <FormGroup controlId={label}>
                <ControlLabel>{label}</ControlLabel>
                <FormControl
                  type="text"
                  value={this.state.value}
                  placeholder="Enter text"
                  onChange={this.handleChange}
                  onKeyPress={this.handleEnter}
                />
              </FormGroup>
            </form>
            <BootstrapButton
              onClick={this.handleClick}
              disabled={!this.isAllowedSubmit()}
              block
            >
                Save
            </BootstrapButton>
        </div>
    )
  }
}

export default Input
