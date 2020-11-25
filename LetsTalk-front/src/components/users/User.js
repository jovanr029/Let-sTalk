import React from "react";
import LetsTalkAxios from "../../apis/LetsTalkAxios";
import PostUser from "../posts/PostUser";

class User extends React.Component {
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
      id: this.props.match.params.id,
    };
  }

  componentDidMount() {
    this.getUser();
  }

  getUser() {
    LetsTalkAxios.get("/users/" + this.state.id)
      .then((res) => {
        this.setState({ user: res.data });
        console.log(this.state.user);
      })
      .catch((error) => {
        console.log("could not get a user");
      });
  }

  render() {
    return (
      <div>
        <h1  className="text-center">{this.state.user.firstName} {this.state.user.lastName}</h1>
        <h4>Username: {this.state.user.username}</h4>
        <h4>Date of birth: {this.state.user.dateOfBirth}</h4>
        <h4>Email: {this.state.user.email}</h4>
        <PostUser id={this.state.id}></PostUser>
      </div>
    );
  }
}
export default User;
