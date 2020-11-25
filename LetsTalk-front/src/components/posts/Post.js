import React from "react";
import LetsTalkAxios from "../../apis/LetsTalkAxios";
import AddComment from "../comments/AddComment";
import Comments from "../comments/Comments.js";

class Post extends React.Component {
  constructor(props) {
    super(props);

    let post = {
      id: "",
      title: "",
      content: "",
      usernname: "",
      user: "",
    };
    this.state = { post: post };
  }

  componentDidMount() {
    this.getPost();
  }

  getPost() {
    LetsTalkAxios.get("/posts/" + this.props.match.params.id)
      .then((res) => {
        this.setState({ post: res.data });
        console.log(this.state.post);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    return (
      <div>
        <h1>Post</h1>
        <AddComment id={this.props.match.params.id}></AddComment>
        <div style={{ border: "2px solid", padding: "10px", marginTop: 25, backgroundColor: "grey" }}>
          <p>{this.state.post.user}</p>
          <p><b>Title: </b>{this.state.post.title}</p>
          <p>{this.state.post.content}</p>
        </div>
        <Comments id={this.props.match.params.id}></Comments>
      </div>
    );
  }
}
export default Post;
