import React from "react";
import LetsTalkAxios from "../../apis/LetsTalkAxios";
import { Button, Form } from "react-bootstrap";

class EditProfile extends React.Component {
  constructor(props) {
    super(props);

    let user = {
      username: "",
      email: "",
      firstName: "",
      lastName: "",
      dateOfBirth: "",
    };

    this.state = {
      user: user,
    };
  }

  componentDidMount() {
    this.getProfile();
  }

  getProfile() {
    LetsTalkAxios.get("/users/profile")
      .then((res) => {
        this.setState({ user: res.data });
        console.log(this.state.user);
      })
      .catch((error) => {
        console.log("could not get a user");
      });
  }

  async saveChanges(){
    try{
      await LetsTalkAxios.put("/users/profile/edit" , this.state.user);
      this.props.history.push("/users");
    }
    catch(error){
      console.log('could not edit a user')
    }
}

onNameChange(event){
    let control = event.target;
    let name = control.id;
    let value = control.value;

    let user = this.state.user;
    user[name] = value;

    this.setState({user: user});
    
};

  render() {
    return (
      <div>
        <h1>Edit User</h1>
        <Form>
          <Form.Group>
            <Form.Label>Username:</Form.Label>
            <Form.Control
              id="username"
              type="text"
              as="input"
              value={this.state.user.username}
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Email:</Form.Label>
            <Form.Control
              id="email"
              type="email"
              as="input"
              value={this.state.user.email}
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>First Name:</Form.Label>
            <Form.Control
              id="firstName"
              type="text"
              as="input"
              value={this.state.user.firstName}
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Last Name:</Form.Label>
            <Form.Control
              id="lastName"
              type="text"
              as="input"
              value={this.state.user.lastName}
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Date of birth:</Form.Label>
            <Form.Control
              id="dateOfBirth"
              type="text"
              as="input"
              value={this.state.user.dateOfBirth}
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
        </Form>
        <Button onClick={() => this.saveChanges()}>Save</Button>
      </div>
    );
  }
}
export default EditProfile;
