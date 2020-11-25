import React from "react";
import LetsTalkAxios from "../../apis/LetsTalkAxios";
import PostProfile from "../posts/PostProfile";
import { Button } from "react-bootstrap";

class MyProfile extends React.Component {
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

  goToEditProfile() {
    this.props.history.push("/profile/edit/");
  }

  render() {
    return (
      <div>
        <h1>Hello {this.state.user.firstName}</h1>
        <h4>First name: {this.state.user.firstName}</h4>
        <h4>Last name: {this.state.user.lastName}</h4>
        <h4>Username: {this.state.user.username}</h4>
        <h4>Date of birth: {this.state.user.dateOfBirth}</h4>
        <h4>Email: {this.state.user.email}</h4>
        <Button
          variant="warning"
          style={{ marginTop: 15, marginBottom: 15 }}
          onClick={() => this.goToEditProfile()}
        >
          Edit
        </Button>
        <PostProfile></PostProfile>
      </div>
    );
  }
}
export default MyProfile;
