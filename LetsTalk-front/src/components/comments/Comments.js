import React from "react";
import LetsTalkAxios from "../../apis/LetsTalkAxios";

class Comments extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      comments: [],
    };
  }

  componentDidMount() {
    this.getComments();
  }

  getComments() {
    LetsTalkAxios.get("/comments/post/" + this.props.id)
      .then((res) => {
        this.setState({ comments: res.data });
        console.log(this.state.comments);
      })
      .catch((error) => {
        console.log("could not get comments");
      });
  }

  renderData() {
    return this.state.comments.map((comment, index) => {
      return (
        <div
          key={comment.id}
          style={{
            border: "2px solid",
            padding: "10px",
            marginTop: 25,
            backgroundColor: "lightgray",
          }}
        >
          <p>{comment.user}:</p>
          <p>
            <b>Comment:</b> {comment.text}
          </p>
        </div>
      );
    });
  }

  render() {
    return <div>{this.renderData()}</div>;
  }
}
export default Comments;
