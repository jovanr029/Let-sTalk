import React from "react";
import { Button } from "react-bootstrap";
import LetsTalkAxios from "../../apis/LetsTalkAxios";

class Posts extends React.Component {
  constructor(props) {
    super(props);

    this.state = { posts: [] };
  }

  componentDidMount() {
    this.getPosts();
  }

  getPosts() {
    LetsTalkAxios.get("/posts")
      .then((res) => {
        this.setState({ posts: res.data });
        console.log(this.state.posts);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  goToAddPost() {
    this.props.history.push("/post/add")
  }

  goToPost(postId) {
    this.props.history.push("/posts/" + postId);
  }

  renderData() {
    return this.state.posts.map((post, index) => {
      return (
        <div
          key={post.id}
          style={{ border: "2px solid", padding: "10px", marginTop: 25 }}
        >
          <p>{post.user}:</p>
          <p><b>Title: </b>{post.title}</p>
          <Button
            onClick={() => this.goToPost(post.id)}
            style={{ marginRight: 25 }}
          >
            See full post and comments
          </Button>
        </div>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>Posts <Button onClick ={() => this.goToAddPost()} >Create your post</Button></h1>
        <div style={{ marginTop: 25 }}>{this.renderData()}</div>
      </div>
    );
  }
}
export default Posts;
