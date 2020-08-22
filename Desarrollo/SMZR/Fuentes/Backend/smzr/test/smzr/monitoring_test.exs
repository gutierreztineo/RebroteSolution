defmodule Smzr.MonitoringTest do
  use Smzr.DataCase

  alias Smzr.Monitoring

  describe "ailments" do
    alias Smzr.Monitoring.Ailment

    @valid_attrs %{description: "some description"}
    @update_attrs %{description: "some updated description"}
    @invalid_attrs %{description: nil}

    def ailment_fixture(attrs \\ %{}) do
      {:ok, ailment} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_ailment()

      ailment
    end

    test "list_ailments/0 returns all ailments" do
      ailment = ailment_fixture()
      assert Monitoring.list_ailments() == [ailment]
    end

    test "get_ailment!/1 returns the ailment with given id" do
      ailment = ailment_fixture()
      assert Monitoring.get_ailment!(ailment.id) == ailment
    end

    test "create_ailment/1 with valid data creates a ailment" do
      assert {:ok, %Ailment{} = ailment} = Monitoring.create_ailment(@valid_attrs)
      assert ailment.description == "some description"
    end

    test "create_ailment/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_ailment(@invalid_attrs)
    end

    test "update_ailment/2 with valid data updates the ailment" do
      ailment = ailment_fixture()
      assert {:ok, %Ailment{} = ailment} = Monitoring.update_ailment(ailment, @update_attrs)
      assert ailment.description == "some updated description"
    end

    test "update_ailment/2 with invalid data returns error changeset" do
      ailment = ailment_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_ailment(ailment, @invalid_attrs)
      assert ailment == Monitoring.get_ailment!(ailment.id)
    end

    test "delete_ailment/1 deletes the ailment" do
      ailment = ailment_fixture()
      assert {:ok, %Ailment{}} = Monitoring.delete_ailment(ailment)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_ailment!(ailment.id) end
    end

    test "change_ailment/1 returns a ailment changeset" do
      ailment = ailment_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_ailment(ailment)
    end
  end

  describe "profile_ailments" do
    alias Smzr.Monitoring.ProfileAilment

    @valid_attrs %{}
    @update_attrs %{}
    @invalid_attrs %{}

    def profile_ailment_fixture(attrs \\ %{}) do
      {:ok, profile_ailment} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_profile_ailment()

      profile_ailment
    end

    test "list_profile_ailments/0 returns all profile_ailments" do
      profile_ailment = profile_ailment_fixture()
      assert Monitoring.list_profile_ailments() == [profile_ailment]
    end

    test "get_profile_ailment!/1 returns the profile_ailment with given id" do
      profile_ailment = profile_ailment_fixture()
      assert Monitoring.get_profile_ailment!(profile_ailment.id) == profile_ailment
    end

    test "create_profile_ailment/1 with valid data creates a profile_ailment" do
      assert {:ok, %ProfileAilment{} = profile_ailment} = Monitoring.create_profile_ailment(@valid_attrs)
    end

    test "create_profile_ailment/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_profile_ailment(@invalid_attrs)
    end

    test "update_profile_ailment/2 with valid data updates the profile_ailment" do
      profile_ailment = profile_ailment_fixture()
      assert {:ok, %ProfileAilment{} = profile_ailment} = Monitoring.update_profile_ailment(profile_ailment, @update_attrs)
    end

    test "update_profile_ailment/2 with invalid data returns error changeset" do
      profile_ailment = profile_ailment_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_profile_ailment(profile_ailment, @invalid_attrs)
      assert profile_ailment == Monitoring.get_profile_ailment!(profile_ailment.id)
    end

    test "delete_profile_ailment/1 deletes the profile_ailment" do
      profile_ailment = profile_ailment_fixture()
      assert {:ok, %ProfileAilment{}} = Monitoring.delete_profile_ailment(profile_ailment)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_profile_ailment!(profile_ailment.id) end
    end

    test "change_profile_ailment/1 returns a profile_ailment changeset" do
      profile_ailment = profile_ailment_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_profile_ailment(profile_ailment)
    end
  end
end
