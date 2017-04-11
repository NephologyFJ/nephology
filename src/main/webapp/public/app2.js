var HelloInput = React.createClass({
    propTypes: {
        initialValue: React.PropTypes.string
      },
      defaultProps: {
        initialValue: ''
      },
      // Set up initial state
      getInitialState: function() {
        return {
          userName: this.props.initialValue || 'placeholder',
          message: this.props.initialValue || 'placeholder'
        };
      },

    handleChange: function(event) {
        this.setState({[event.target.name]: event.target.value});
     },

    handleSubmit: function (e){

        e.preventDefault()

        $.ajax({
            url: "/api/helloes",
            method: 'POST',
            headers: {'Content-Type' : 'application/json;charset=UTF-8'},
            data: JSON.stringify(this.state),
            success: function(result) {
                toastr.info('New Hello Added');
            },
            error: function(xhr, ajaxOptions, thrownError) {
                toastr.error(xhr.responseJSON.message);
             }
            })
            .done(function(data) {
                window.location.href = '/'
        })
  },

  render: function() {
    return (
        <div className="container">
        <form className ="form" onSubmit={this.handleSubmit}>
            <label>
                Your Name:&emsp;
                <input type="text" name="userName" id='userName' value={this.state.value} onChange={this.handleChange}/>
            </label>&emsp;
            <label>
                Message:&emsp;
                <input type="text" name="message" id='message' value={this.state.value} onChange={this.handleChange}/>
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