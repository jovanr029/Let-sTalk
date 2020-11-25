import React from "react";
import LetsTalkAxios from "../../apis/LetsTalkAxios";
import { Button } from "react-bootstrap";

class PostProfile extends React.Component {
  constructor(props) {
    super(props);

    this.state = { posts: [] };
  }

  componentDidMount() {
    this.getPosts();
  }

  getPosts() {
    LetsTalkAxios.get("/posts/profile/")
      .then((res) => {
        this.setState({ posts: res.data });
        console.log(this.state.posts);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  async doDelete(postId){
    try{
      await LetsTalkAxios.delete("/posts/" + postId);
      this.getPosts();
    } catch (error) {
      console.log("could not delete a post")
    }
  }

  goToEditPost(postId) {
    window.location.replace("/#/post/edit/" + postId);
  }

  renderData() {
    return this.state.posts.map((post, index) => {
      return (
        <div
          key={post.id}
          style={{ border: "2px solid", padding: "10px", marginTop: 25 }}
        >
          <p>{post.user}:</p>
          <p><b>Title: </b>{post.title}:</p>
          <p>{post.content}</p>
          <Button variant="warning" onClick={() => this.goToEditPost(post.id)}>
            Edit
          </Button>
          <Button variant="danger" onClick={() => this.doDelete(post.id)}>
            Delete
          </Button>
        </div>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>Posts</h1>
        <div style={{ marginTop: 25 }}>{this.renderData()}</div>
      </div>
    );
  }
}
export default PostProfile;
