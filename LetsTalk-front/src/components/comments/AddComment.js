import React from "react";
import { Button, Form } from "react-bootstrap";
import LetsTalkAxios from "../../apis/LetsTalkAxios";

class AddComment extends React.Component {
  constructor(props) {
    super(props);

    let comment = {
      postId: this.props.id,
      text: "",
    };

    this.state = {
      comment: comment,
    };
  }

  add() {
    LetsTalkAxios.post("/comments/post", this.state.comment)
      .then((res) => {
        console.log(res);
        window.location.reload();
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  onNameChange(event) {
    let control = event.target;
    let name = control.id;
    let value = control.value;

    let comment = this.state.comment;
    comment[name] = value;

    this.setState({ comment: comment });
  }

  render() {
    return (
      <div>
        <Form>
          <Form.Group>
            <Form.Label>Add comment</Form.Label>
            <Form.Control
              id="text"
              type="text"
              as="input"
              onChange={(e) => this.onNameChange(e)}
            ></Form.Control>
          </Form.Group>
          <Button onClick={() => this.add()}>AddComment</Button>
        </Form>
      </div>
    );
  }
}
export default AddComment;
