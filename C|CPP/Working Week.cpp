#include <bits/stdc++.h>
 
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
 
using namespace std;
using namespace __gnu_pbds;
 
struct custom_hash {
  static uint64_t splitmix64(uint64_t x) {
    x += 0x9e3779b97f4a7c15;
    x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9;
    x = (x ^ (x >> 27)) * 0x94d049bb133111eb;
    return x ^ (x >> 31);
  }
 
  size_t operator()(uint64_t x) const {
    static const uint64_t FIXED_RANDOM =
        chrono::steady_clock::now().time_since_epoch().count();
    return splitmix64(x + FIXED_RANDOM);
  }
};
 
#define all(x) begin(x), end(x)
#define rall(x) rbegin(x), rend(x)
 
#define F(i, a) for (int i = 0; i < a; ++i)
#define F1(i, a, b) for (int i = a; i < b; ++i)
#define FR(i, a) for (int i = a; i >= 0; --i)
#define FR1(i, a, b) for (int i = a; i >= b; --i)
 
#define trav(a, x) for (auto& a : x)
 
#define sz(x) (int)x.size()
#define pf push_front
#define pb push_back
#define eb emplace_back
 
typedef long long ll;
typedef long double ld;
typedef double db;
typedef string str;
 
const int MOD = 1e9 + 7;
const ll BIG = 1e18;
const vector<pair<int, int>> dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
 
template <class T0, class T1>
using pr = pair<T0, T1>;
template <class T>
using vt = vector<T>;
template <class T>
using us = unordered_set<T, custom_hash>;
template <class T>
using ms = multiset<T>;
template <class T0, class T1>
using um = unordered_map<T0, T1, custom_hash>;
template <class T>
using pq = priority_queue<T, vector<T>>;
template <class T>
using pqg = priority_queue<T, vector<T>, greater<T>>;
 
typedef tree<int, null_type, less_equal<int>, rb_tree_tag,
             tree_order_statistics_node_update>
    oms;
 
typedef tree<int, null_type, less<int>, rb_tree_tag,
             tree_order_statistics_node_update>
    os;
 
template <class A>
void re(complex<A>& c);
template <class A, class B>
void re(pair<A, B>& p);
template <class A>
void re(vector<A>& v);
template <class A, size_t SZ>
void re(array<A, SZ>& a);
 
template <class T>
void re(T& x) {
  cin >> x;
}
void re(db& d) {
  str t;
  re(t);
  d = stod(t);
}
void re(ld& d) {
  str t;
  re(t);
  d = stold(t);
}
template <class H, class... T>
void re(H& h, T&... t) {
  re(h);
  re(t...);
}
 
template <class A>
void re(complex<A>& c) {
  A a, b;
  re(a, b);
  c = {a, b};
}
template <class A, class B>
void re(pair<A, B>& p) {
  re(p.f, p.s);
}
template <class A>
void re(vector<A>& x) {
  trav(a, x) re(a);
}
template <class A, size_t SZ>
void re(array<A, SZ>& x) {
  trav(a, x) re(a);
}
 
template <class T>
void print(vector<T>& arr) {
  cout << "[";
  for (int i = 0; i < arr.size(); ++i) {
    auto& v = arr[i];
    if (i > 0) cout << ", ";
    cout << v;
  }
  cout << "]" << endl;
}
 
template <class T>
void print(unordered_set<T>& se) {
  cout << "{";
  for (auto v = se.begin(); v != se.end(); v++) {
    if (v != se.begin()) cout << ", ";
    cout << *v;
  }
  cout << "}" << endl;
}
 
template <class Key, class Value>
void print(unordered_map<Key, Value>& mp) {
  cout << "{" << endl;
  for (auto& [k, v] : mp) {
    cout << k << ": " << v << ", " << endl;
  }
  cout << "}" << endl;
}
 
template <class T>
void print(T v) {
  cout << v << endl;
}
 
template <typename T0, typename... T1>
void print(const T0& arg0, const T1&... args) {
  auto space_before = [](const auto& arg) -> const auto& {
    cout << " ";
    return arg;
  };
  cout << arg0;
  (cout << ... << space_before(args)) << endl;
}
 
template <class T>
void print_arr(vt<T>& a) {
  F(i, sz(a)) {
    if (i != 0) cout << " ";
    cout << a[i];
  }
  cout << endl;
}
 
class UniFind {
 public:
  UniFind(int n) {
    par_.reserve(n);
    size_.reserve(n);
    F(i, n) {
      par_[i] = i;
      size_[i] = 1;
    }
  }
 
  int find(int a) {
    if (par_[a] == a) {
      return a;
    }
    par_[a] = find(par_[a]);
    return par_[a];
  }
 
  void uni(int a, int b) {
    int par_a = find(a);
    int par_b = find(b);
    if (par_a != par_b) {
      par_[par_a] = par_b;
      size_[par_b] += size_[par_a];
    }
  }
 
  int size(int a) { return size_[a]; }
 
 private:
  vt<int> par_;
  vt<int> size_;
};
 
class SparseTable {
 public:
  SparseTable(vt<int>& arr) {
    int n = sz(arr);
    int k = log2(n) + 1;
    vt<vt<int>> lookup(n, vt<int>(k));
    for (int i = 0; i < n; i++) {
      lookup[i][0] = arr[i];
    }
    for (int j = 1; (1 << j) <= n; j++) {
      for (int i = 0; (i + (1 << j) - 1) < n; i++) {
        if (lookup[i][j - 1] > lookup[i + (1 << (j - 1))][j - 1]) {
          lookup[i][j] = lookup[i][j - 1];
        } else {
          lookup[i][j] = lookup[i + (1 << (j - 1))][j - 1];
        }
      }
    }
    lookup_ = move(lookup);
  }
 
  int QueryMax(int l, int r) {
    int j = log2(r - l + 1);
    if (lookup_[l][j] >= lookup_[r - (1 << j) + 1][j]) {
      return lookup_[l][j];
    } else {
      return lookup_[r - (1 << j) + 1][j];
    }
  }
 
 private:
  vt<vt<int>> lookup_;
};
 
bool valid(int n, int m, int i, int j) {
  return i >= 0 && i < n && j >= 0 && j < m;
}
 
template <class T>
void setb(T& v, int k) {
  v |= 1 << k;
}
 
template <class T>
int getb(T v, int k) {
  return (v >> k) & 1;
}
 
template <class T>
void unset(T& v, int k) {
  v &= ~(1 << k);
}
 
template <class T>
void toggle(T& v, int k) {
  v ^= 1 << k;
}
 
void solve() {
  int n; re(n);
 
  print((n - 6) / 3);
}
 
int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);
 
  int t;
  re(t);
  F(i, t) { solve(); }
}