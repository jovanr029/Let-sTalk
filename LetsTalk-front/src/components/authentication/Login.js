import React from "react";
import { Button, Form } from "react-bootstrap";
import { login } from "../../services/auth";

class Login extends React.Component {
  constructor() {
    super();

    this.state = { username: "", password: "" };
  }

  onHandleChange(event) {
    let control = event.target;
    let name = control.id;
    let value = control.value;

    let change = {};
    change[name] = value;

    this.setState(change);
  }

  doLogin() {
    login(this.state);
  }

  render() {
    return (
      <div>
        <h1>Welcome</h1>
        <Form>
          <Form.Group>
            <Form.Label>Username:</Form.Label>
            <Form.Control
              id="username"
              as="input"
              onChange={(e) => this.onHandleChange(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Password:</Form.Label>
            <Form.Control
              id="password"
              as="input"
              type="password"
              onChange={(e) => this.onHandleChange(e)}
            ></Form.Control>
          </Form.Group>
        </Form>
        <Button onClick={() => this.doLogin()}>LogIn</Button>
        <br />
        <br />
        <p>
          You don't have accout? Register <a href="/#/registration">here</a>
        </p>
      </div>
    );
  }
}
export default Login;
