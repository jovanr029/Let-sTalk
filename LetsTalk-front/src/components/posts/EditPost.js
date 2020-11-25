import React from "react";
import { Button, Form } from "react-bootstrap";
import LetsTalkAxios from "../../apis/LetsTalkAxios";

class EditPost extends React.Component {
  constructor(props) {
    super(props);

    let post = {
      id: this.props.match.params.id,
      title: "",
      content: "",
    };
    this.state = {
      post: post,
    };
  }

  componentDidMount() {
    this.getPost();
  }

  getPost() {
    LetsTalkAxios.get("/posts/" + this.props.match.params.id)
      .then((res) => {
        console.log(res);
        this.setState({ post: res.data });
      })
      .catch((error) => {
        console.log("could not get a post");
      });
  }

  onNameChange(event) {
    let control = event.target;
    let name = control.id;
    let value = control.value;

    let post = this.state.post;
    post[name] = value;
    this.setState({ post: post });
  }

  async doEdit() {
    try {
      await LetsTalkAxios.put("/posts/" + this.props.match.params.id, this.state.post);
      this.props.history.push("/posts");
    } catch (error) {
      console.log("Could not edit a post");
    }
  }
  render() {
    return (
      <div>
        <h1>Edit your post</h1>
        <Form>
          <Form.Group>
            <Form.Label>Title:</Form.Label>
            <Form.Control
              id="title"
              type="text"
              value={this.state.post.title}
              as="input"
              onChange={(e) => this.onNameChange(e)}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Content:</Form.Label>
            <Form.Control
              id="content"
              type="text"
              value={this.state.post.content}
              as="input"
              onChange={(e) => this.onNameChange(e)}
            />
          </Form.Group>
          <Button onClick={() => this.doEdit()}>Save</Button>
        </Form>
      </div>
    );
  }
}
export default EditPost;
