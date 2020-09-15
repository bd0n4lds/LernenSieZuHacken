import unittest
   2 from foobarbaz import Foo  # code from module you're testing
   3
   4
   5 class SimpleTestCase(unittest.TestCase):
   6
   7 def setUp(self):
   8         """Call before every test case."""
   9         self.foo = Foo()
  10         self.file = open( "blah", "r" )
  11 
  12     def tearDown(self):
  13         """Call after every test case."""
  14         self.file.close()
  15 
  16     def testA(self):
  17         """Test case A. note that all test method names must begin with 'test.'"""
  18         assert foo.bar() == 543, "bar() not calculating values correctly"
  19 
  20     def testB(self):
  21         """test case B"""
  22         assert foo+foo == 34, "can't add Foo instances"
  23 
  24     def testC(self):
  25         """test case C"""
  26         assert foo.baz() == "blah", "baz() not returning blah correctly"
  27 
  28 
  29 class OtherTestCase(unittest.TestCase):
  30 
  31     def setUp(self):
  32         blah_blah_blah()
  33 
  34     def tearDown(self):
  35         blah_blah_blah()
  36 
  37     def testBlah(self):
  38         assert self.blahblah == "blah", "blah isn't blahing blahing correctly"
  39 
  40 
  41 if __name__ == "__main__":
  42     unittest.main() # run all tests
