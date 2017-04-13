var InstanceLine = React.createClass({
  getInitialState: function() {
    return {display: true };
  },

  render: function() {
    console.log(this);
    if (this.state.display==false) return null;
    else return (
      <tr>
        <td>{this.props.instance.instanceId}</td>
        <td>{this.props.instance.imageId}</td>
        <td>{this.props.instance.keyName}</td>
        <td>{this.props.instance.instanceType}</td>
        <td>{this.props.instance.subnetId}</td>
        <td>{this.props.instance.privateIpAddress}</td>
        <td>{this.props.instance.publicIpAddress}</td>
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
                <th>Instance ID</th>
                <th>Image ID</th>
                <th>Key Name</th>
                <th>Instance Type</th>
                <th>Subnet ID</th>
                <th>Private IP Address</th>
                <th>Public IP Address</th>
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
      url: "/api/awsEC2InstanceDetailsDatas"
    }).then(function (data) {
      self.setState({instances: data._embedded.awsEC2InstanceDetailsDatas});
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
            <h2>Instances</h2>
            <InstanceTable instances={this.state.instances}/>
        </div>
        );
  }
});
ReactDOM.render(<Instance />, document.getElementById('root3'));