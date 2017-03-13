<script type="text/babel">
var HelloInput = React.createClass({

   submit: function (e){
   var self

   e.preventDefault()
   self = this

   var data = {
    userName: this.state.userName,
    message: this.state.message
   }

   var self = this;
   $.ajax({
    url: "/api/helloes",
    type: 'POST',
    data: data,
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
        <div className="toast-container div rtl">
        <form onSubmit={this.submit}>
            <label class="toast-message">Your Name:&emsp;
                <input type="text" name="userName" class="toast-message" id='userName'/>
            </label><br />
            <label class="toast-message">Message:&emsp;
                <input type="text" name="message" id='message'/>
            </label><br />
            <input type="submit" id='saveName' className="btn btn-info" value="Submit"/>
        </form>
        </div>
    );
  }
});

ReactDOM.render(
  <HelloInput />, document.getElementById('root2')
);
</script>