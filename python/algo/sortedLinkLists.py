#Given two sorted linked lists, merge them in order.
#This can be done recursively and iteratively. See if you can get both solutions.
# Definition for singly-linked list.
class ListNode(object):
  def __init__(self, x):
    self.val = x
    self.next = None

class Solution:
  def mergeTwoLists(self, l1, l2):
    if l1 is None:
      return l2
    elif l2 is None:
      return l1
    elif l1.val < l2.val:
      l1.next = self.mergeTwoLists(l1.next, l2)
      return l1
    else:
      l2.next = self.mergeTwoLists(l1, l2.next)
      return l2

  def mergeTwoListsIterative(self, l1, l2):
    current = None
    root = None
    while True:
      if l1 is None:
        nextNode = l2
      elif l2 is None:
        nextNode = l1
      elif l1.val < l2.val:
        nextNode = l1
      else:
        nextNode = l2

      if nextNode == l1:
        l1 = l1.next if l1 else None
      if nextNode == l2:
        l2 = l2.next if l2 else None

      if nextNode is None:
        break
      if not current:
        current = nextNode
        root = current
      else:
        current.next = nextNode
      current = nextNode
    return root

# Test program
a = ListNode(1)
a.next = ListNode(3)
a.next.next = ListNode(5)

b = ListNode(2)
b.next = ListNode(4)
b.next.next = ListNode(6)

c = Solution().mergeTwoListsIterative(a, b)
while c:
  print c.val
  c = c.next
