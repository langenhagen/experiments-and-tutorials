#include <iostream>
#include <gtest/gtest.h>


struct Account {

    int balance = 0;

    Account()
    {}

    explicit Account(const int balance)
        : balance(balance)
    {}

};


TEST(AccountTest, AccountStartsEmpty)
{
    Account acc;
    EXPECT_EQ(0, acc.balance);
}


int main(int argc, char* argv[]) {

    testing::InitGoogleTest(&argc, argv);

    auto i = RUN_ALL_TESTS();

    std::cout << std::endl << "RUN_ALL_TESTS() = " << i << std::endl;

    std::cin.get();
    return i;
}
