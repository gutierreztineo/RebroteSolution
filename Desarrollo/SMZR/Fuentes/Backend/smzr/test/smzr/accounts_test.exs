defmodule Smzr.AccountsTest do
  use Smzr.DataCase

  alias Smzr.Accounts

  describe "users" do
    alias Smzr.Accounts.User

    @valid_attrs %{is_active: true, password_hash: "some password_hash", username: "some username"}
    @update_attrs %{is_active: false, password_hash: "some updated password_hash", username: "some updated username"}
    @invalid_attrs %{is_active: nil, password_hash: nil, username: nil}

    def user_fixture(attrs \\ %{}) do
      {:ok, user} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Accounts.create_user()

      user
    end

    test "list_users/0 returns all users" do
      user = user_fixture()
      assert Accounts.list_users() == [user]
    end

    test "get_user!/1 returns the user with given id" do
      user = user_fixture()
      assert Accounts.get_user!(user.id) == user
    end

    test "create_user/1 with valid data creates a user" do
      assert {:ok, %User{} = user} = Accounts.create_user(@valid_attrs)
      assert user.is_active == true
      assert user.password_hash == "some password_hash"
      assert user.username == "some username"
    end

    test "create_user/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Accounts.create_user(@invalid_attrs)
    end

    test "update_user/2 with valid data updates the user" do
      user = user_fixture()
      assert {:ok, %User{} = user} = Accounts.update_user(user, @update_attrs)
      assert user.is_active == false
      assert user.password_hash == "some updated password_hash"
      assert user.username == "some updated username"
    end

    test "update_user/2 with invalid data returns error changeset" do
      user = user_fixture()
      assert {:error, %Ecto.Changeset{}} = Accounts.update_user(user, @invalid_attrs)
      assert user == Accounts.get_user!(user.id)
    end

    test "delete_user/1 deletes the user" do
      user = user_fixture()
      assert {:ok, %User{}} = Accounts.delete_user(user)
      assert_raise Ecto.NoResultsError, fn -> Accounts.get_user!(user.id) end
    end

    test "change_user/1 returns a user changeset" do
      user = user_fixture()
      assert %Ecto.Changeset{} = Accounts.change_user(user)
    end
  end

  describe "profiles" do
    alias Smzr.Accounts.Profile

    @valid_attrs %{birthdate: ~D[2010-04-17], dni: "some dni", email: "some email", firstname: "some firstname", lastnamem: "some lastnamem", lastnamep: "some lastnamep"}
    @update_attrs %{birthdate: ~D[2011-05-18], dni: "some updated dni", email: "some updated email", firstname: "some updated firstname", lastnamem: "some updated lastnamem", lastnamep: "some updated lastnamep"}
    @invalid_attrs %{birthdate: nil, dni: nil, email: nil, firstname: nil, lastnamem: nil, lastnamep: nil}

    def profile_fixture(attrs \\ %{}) do
      {:ok, profile} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Accounts.create_profile()

      profile
    end

    test "list_profiles/0 returns all profiles" do
      profile = profile_fixture()
      assert Accounts.list_profiles() == [profile]
    end

    test "get_profile!/1 returns the profile with given id" do
      profile = profile_fixture()
      assert Accounts.get_profile!(profile.id) == profile
    end

    test "create_profile/1 with valid data creates a profile" do
      assert {:ok, %Profile{} = profile} = Accounts.create_profile(@valid_attrs)
      assert profile.birthdate == ~D[2010-04-17]
      assert profile.dni == "some dni"
      assert profile.email == "some email"
      assert profile.firstname == "some firstname"
      assert profile.lastnamem == "some lastnamem"
      assert profile.lastnamep == "some lastnamep"
    end

    test "create_profile/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Accounts.create_profile(@invalid_attrs)
    end

    test "update_profile/2 with valid data updates the profile" do
      profile = profile_fixture()
      assert {:ok, %Profile{} = profile} = Accounts.update_profile(profile, @update_attrs)
      assert profile.birthdate == ~D[2011-05-18]
      assert profile.dni == "some updated dni"
      assert profile.email == "some updated email"
      assert profile.firstname == "some updated firstname"
      assert profile.lastnamem == "some updated lastnamem"
      assert profile.lastnamep == "some updated lastnamep"
    end

    test "update_profile/2 with invalid data returns error changeset" do
      profile = profile_fixture()
      assert {:error, %Ecto.Changeset{}} = Accounts.update_profile(profile, @invalid_attrs)
      assert profile == Accounts.get_profile!(profile.id)
    end

    test "delete_profile/1 deletes the profile" do
      profile = profile_fixture()
      assert {:ok, %Profile{}} = Accounts.delete_profile(profile)
      assert_raise Ecto.NoResultsError, fn -> Accounts.get_profile!(profile.id) end
    end

    test "change_profile/1 returns a profile changeset" do
      profile = profile_fixture()
      assert %Ecto.Changeset{} = Accounts.change_profile(profile)
    end
  end
end
