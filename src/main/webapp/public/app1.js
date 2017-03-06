var Hello = React.createClass({
  getInitialState: function() {
    return {display: true };
  },
  handleDelete() {
    var self = this;
    $.ajax({
      url: self.props.hello._links.self.href,
      type: 'DELETE',
      success: function(result) {
        self.setState({display: false});
      },
      error: function(xhr, ajaxOptions, thrownError) {
        toastr.error(xhr.responseJSON.message);
      }
    });
  },
  render: function() {
    if (this.state.display==false) return null;
    else return (
      <tr>
        <td>{this.props.hello.message}</td>
        <td>
          <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
        </td>
      </tr>
    );
  }
});
var HelloTable = React.createClass({
  render: function() {
    var rows = [];
    this.props.helloes.forEach(function(hello) {
      rows.push(<Hello hello={hello} />);
    });
    return (
      <div className="container">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Message</th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
          </table>
      </div>
      );
  }
});
var App = React.createClass({

  loadHelloesFromServer: function () {
    var self = this;
    $.ajax({
      url: "/api/helloes"
    }).then(function (data) {
      self.setState({helloes: data._embedded.helloes});
    });
  },

  getInitialState: function () {
    return {helloes: []};
  },

  componentDidMount: function () {
    this.loadHelloesFromServer();
  },

  render() {
    return ( <HelloTable helloes={this.state.helloes}/> );
  }
});
ReactDOM.render(<App />, document.getElementById('root') );