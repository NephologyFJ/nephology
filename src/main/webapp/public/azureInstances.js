var InstanceLine = React.createClass({
  getInitialState: function() {
    return {display: true };
  },

  render: function() {
    console.log(this);
    if (this.state.display==false) return null;
    else return (
      <tr>
        <td>{this.props.instance.vmId}</td>
        <td>{this.props.instance.name}</td>
        <td>{this.props.instance.status}</td>
        <td>{this.props.instance.size}</td>
        <td>{this.props.instance.location}</td>
      </tr>
    );
  }
});

var InstanceTable = React.createClass({
  render: function() {
    var rows = [];
    this.props.instances.forEach(function(instance) {
      rows.push(<InstanceLine instance={instance} />);
    });
    return (
      <div className="container">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Status</th>
                <th>Size</th>
                <th>Location</th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
          </table>
      </div>
      );
  }
});
var Instance = React.createClass({

  loadInstancesFromServer: function () {
    var self = this;
    $.ajax({
      url: "/api/azureVirtualMachineDatas"
    }).then(function (data) {
      self.setState({instances: data._embedded.azureVirtualMachineDatas});
    });
  },

/*  loadInstancesFromServer: function () {
        this.setState({instances : [
                            {instanceId: "SomeId", imageId: "SomeImageId", keyName: "SomeKeyName", instanceType: "Type", subnetId: "SomeSubnet"},
                            {instanceId: "SomeId2", imageId: "SomeImageId2", keyName: "SomeKeyName2", instanceType: "Type2", subnetId: "SomeSubnet2"}
        ]});
        console.log('Load Instances: '+this);
  },**/

  getInitialState: function () {
    return {instances: []};
  },

  componentDidMount: function () {
    this.loadInstancesFromServer();
  },

  render() {
    return (
        <div className="container">
            <h2>Azure Instances</h2>
            <InstanceTable instances={this.state.instances}/>
        </div>
        );
  }
});
ReactDOM.render(<Instance />, document.getElementById('root4'));