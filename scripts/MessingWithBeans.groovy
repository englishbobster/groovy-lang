//demonstrate getting properties from classes
class BroadBean {
  String colour, size

  def getSize() {
    return colour + ' ' + size + '!'
  }
}

def theBroadBean = new BroadBean()
theBroadBean.setColour('red')
theBroadBean.setSize('large')

assert theBroadBean.getProperty('colour') == 'red'
assert theBroadBean.getSize() == 'red large!'

//. notation still uses the accessor methods(get/set) under the hood
theBroadBean.size = 'tiny'
assert theBroadBean.size == 'red tiny!'

//to get access directly to the fields use .@
assert theBroadBean.@size == 'tiny'
