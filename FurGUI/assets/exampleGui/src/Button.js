import React, {Component} from "react"
import {
  Col,
  Button as BootstrapButton,
} from 'react-bootstrap';

class Button extends Component {
  constructor(props){
      super(props)
  }

  render() {
    let {label, speaking, onClick} = this.props

    return (
      <Col sm={12} className={"buttonContainer"}>
        <BootstrapButton
            key={label}
            className={`button`}
            onClick={() => { onClick(label) }}
            disabled={ speaking }
        >
          { label }
        </BootstrapButton>
      </Col>
    )
  }
}

export default Button
