import React from "react";
import { Button, Form } from "react-bootstrap";
import LetsTalkAxios from "../../apis/LetsTalkAxios";

class Registration extends React.Component {
  constructor(props) {
    super(props);

    let user = {
      username: "",
      email: "",
      firstName: "",
      lastName: "",
      dateOfBirth: "",
      password: "",
      passwordConfirm: "",
    };

    this.state = {
      user: user,
    };
  }

  saveUser() {
    LetsTalkAxios.post("/users/", this.state.user)
      .then((res) => {
        this.props.history.push("/users/");
      })
      .catch((error) => {
        alert("Couldn't register a user.");
      });
  }

  onNameChange(event) {
    let control = event.target;
    let name = control.id;
    let value = control.value;

    let user = this.state.user;
    user[name] = value;

    this.setState({ user: user });
  }

  render() {
    return (
      <div>
        <h1>Add User</h1>
        <Form>
          <Form.Group>
            <Form.Label>Username:</Form.Label>
            <Form.Control
              id="username"
              type="text"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Email:</Form.Label>
            <Form.Control
              id="email"
              type="email"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>First name:</Form.Label>
            <Form.Control
              id="firstName"
              type="text"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Last name:</Form.Label>
            <Form.Control
              id="lastName"
              type="text"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Date of Birth:</Form.Label>
            <Form.Control
              id="dateOfBirth"
              type="text"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Password:</Form.Label>
            <Form.Control
              id="password"
              type="password"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Password Confirm:</Form.Label>
            <Form.Control
              id="passwordConfirm"
              type="password"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
        </Form>
        <Button onClick={() => this.saveUser()}>Save</Button>
      </div>
    );
  }
}
export default Registration;
