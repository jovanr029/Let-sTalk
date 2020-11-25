import React from "react";
import { Button, Form } from "react-bootstrap";
import LetsTalkAxios from "../../apis/LetsTalkAxios";

class AddPost extends React.Component {
  constructor(props) {
    super(props);

    let post = {
      title: "",
      content: "",
    };
    this.state = {
      post: post,
    };
  }

  onNameChange(event) {
    let control = event.target;
    let name = control.id;
    let value = control.value;

    let post = this.state.post;
    post[name] = value;
    this.setState({ post: post });
  }

  async doAdd() {
    try {
      await LetsTalkAxios.post("/posts", this.state.post);
      this.props.history.push("/posts");
    } catch (error) {
      console.log("Could not add a post");
    }
  }
  render() {
    return (
      <div>
        <h1>Here you can create your post</h1>
        <Form>
          <Form.Group>
            <Form.Label>Title:</Form.Label>
            <Form.Control
              id="title"
              type="text"
              placeholder="enter a title of your post:"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Content:</Form.Label>
            <Form.Control
              id="content"
              type="text"
              placeholder="enter your post"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            />
          </Form.Group>
          <Button onClick={() => this.doAdd()}>Save</Button>
        </Form>
      </div>
    );
  }
}
export default AddPost;
