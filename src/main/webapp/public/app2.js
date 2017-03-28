var HelloInput = React.createClass({

   handleSubmit: function (e){
   var self = this;

   e.preventDefault()

   var data = {
    userName: this.state.userName,
    message: this.state.message
   }

   $.ajax({
    url: "/api/helloes",
    type: 'POST',
    data: data,
    success: function(result) {
        self.setState({display: false});
    },
    error: function(xhr, ajaxOptions, thrownError) {
        toastr.error(xhr.responseJSON.message);
     }
    })
    .done(function(data) {
        self.clearForm()
    })
  },

  componentDidMount: function () {
    this.setState({
         userName: document.getElementById('userName').value,
         message: document.getElementById('message').value
      });
  },

  render: function() {
    return (
        <div className="container">
        <form className ="form" onSubmit={this.handleSubmit}>
            <label>
                Your Name:&emsp;
                <input type="text" name="userName" id='userName'/>
            </label>&emsp;
            <label>
                Message:&emsp;
                <input type="text" name="message" id='message'/>
            </label>&emsp;
            <input type="submit" id='saveName' className="btn btn-success" value="Submit"/>
        </form>
        </div>
    );
  }
});

ReactDOM.render(
  <HelloInput />, document.getElementById('root2')
);